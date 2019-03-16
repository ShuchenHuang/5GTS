package com.wta.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.wta.crud.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wta.crud.bean.Msg;
import com.wta.crud.bean.RealFrame;
import com.wta.crud.service.FrameService;

@Controller
public class FrameContoller {

	@Autowired
	FrameService frameService;

	/**
	 * 返回概率密度函数横纵坐标数据 模拟数据：xSimulation, ySimulation 真实数据：xReal, yReal
	 * 返回评价指标KLD，值为kl
	 *
	 * @param intervalCount
	 *            前端请求需要画图需要多少个横坐标（区间）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public Msg getProbabilityWithJSON(@RequestParam(value = "intervalCount", defaultValue = "10") Integer intervalCount,
									  HttpServletRequest request) throws Exception {
		List<Double> listXs = new ArrayList<>();
		List<Double> listYs = new ArrayList<>();
		List<Double> listXr = new ArrayList<>();
		List<Double> listYr = new ArrayList<>();

		int FrameCount = frameService.getFrameCount("ipb_real_frame1");
		int[] list = getAllFrame(request, FrameCount);
		int[] simulatedFrames = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			simulatedFrames[i] = list[i];
		}

		// 将获取的double[][] 转存到list中，其中第0列是x轴坐标，第1列是y轴坐标
		double[][] ps = new double[intervalCount][];
		ps = FrameProbabilityDistribution.statisticPD(simulatedFrames, FrameCount, intervalCount);
		for (int i = 0; i < ps.length; i++) {
			listXs.add(i, ps[i][0]);
			listYs.add(i, ps[i][1]);
		}

		int[] realFrames = frameService.getColumn("ipb_real_frame1", FrameCount);

		double[][] pr = new double[intervalCount][];
		pr = FrameProbabilityDistribution.statisticPD(realFrames, FrameCount, intervalCount);
		for (int i = 0; i < pr.length; i++) {
			listXr.add(i, pr[i][0]);
			listYr.add(i, pr[i][1]);
		}

		double kl = 0.0;
		for (int i = 0; i < intervalCount; i++) {
			double a = Math.log(listYs.get(i) / listYr.get(i));
			kl += listYs.get(i) * a;
		}

		return Msg.success().add("xSimulation", listXs).add("ySimulation", listYs).add("xReal", listXr)
				.add("yReal", listYr).add("KLD", kl);
	}

	/**
	 * 获取模拟IPB帧序列返回JSON 每秒24帧，1分钟为1440帧，count最好设置为1440的倍数
	 *
	 * @param count
	 *            需要查询出的帧的数量
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/simulatedFrames", method = RequestMethod.GET)
	public Msg getSimulateFramesWithJSON(@RequestParam(value = "count", defaultValue = "24") Integer count,
										 HttpServletRequest request) throws Exception {

		int[] list = getAllFrame(request, count);

		return Msg.success().add("frames", list);
	}

	/**
	 * 获取模拟I 帧序列返回JSON
	 *
	 * @param count
	 *            需要查询出的帧的数量
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/simulatedFrameI", method = RequestMethod.GET)
	public Msg getSimulateFrameIWithJSON(@RequestParam(value = "count", defaultValue = "10") Integer count,
										 HttpServletRequest request) throws Exception {
		String readTxtPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/ModelData/actual_coef.txt");

		int iFrameCount = frameService.getFrameCount("i_real_frame1");
		int iFirstFrame = frameService.getFirstFrame("i_real_frame1");
		System.out.println(iFrameCount);
		System.out.println(iFirstFrame);

		List<Double> frameI = GenerateSimulatedFrameIUtils.waveletReconstruction(readTxtPath);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < frameI.size(); i++) {
			list.add(i, frameI.get(i).intValue());
		}

		return Msg.success().add("frames", list);
	}

	/**
	 * 从数据库中获取真实ipb帧序列
	 *
	 * @param count
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/frames", method = RequestMethod.GET)
	public Msg getRealFramesWithJSON(@RequestParam(value = "count", defaultValue = "10") Integer count) {
		List<RealFrame> frames = frameService.getFrame(count);
		// p, b 帧除以100 I帧除以 1000

		return Msg.success().add("frames", frames);
	}
	/*取得智慧家居的参数*/
	/*@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getSections(ServletRequest request) {

		//用ServletRequest接收参数
		String resultnumber = request.getParameter("number");
		System.out.println(resultnumber);
		Integer renumber= new Integer(resultnumber );
		//处理参数
		return "ok_"+renumber;
	}*/

	/**
	 * 封装获取模拟P or B 帧序列
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int[] getSimulatedFrame(HttpServletRequest request, String FrameFile, int FrameCount) throws Exception {
		// 获取资源文件路径，获取的是服务器中工程路径，便于移植工程
		String readTxtPath = " ";
		if (FrameFile == "p_real_frame1") {
			readTxtPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/classes/ModelData/pmatrix1.txt");
		}
		if (FrameFile == "b_real_frame1") {
			readTxtPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/classes/ModelData/bmatrix1.txt");
		}
		System.out.println(readTxtPath);
		// int FrameCount = frameService.getFrameCount(FrameFile);
		int FirstFrame = frameService.getFirstFrame(FrameFile);

		System.out.println(FrameCount);
		System.out.println(FirstFrame);
		int[] framemodel = new int[FrameCount];
		framemodel = GenerateSimulatedFrameUtils.generatedSequence(readTxtPath, FrameCount, FirstFrame);

		return framemodel;
	}

	/**
	 *
	 */
	public int[] getAllFrame(HttpServletRequest request, int count) throws Exception {
		String readTxtPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/ModelData/actual_coef.txt");
		int iFrameCount = frameService.getFrameCount("i_real_frame1");
		int iFirstFrame = frameService.getFirstFrame("i_real_frame1");
		System.out.println(iFrameCount);
		System.out.println(iFirstFrame);

		List<Double> framemodelI = GenerateSimulatedFrameIUtils.waveletReconstruction(readTxtPath);

		// int[] framemodelP = new int[count];
		// int[] framemodelB = new int[count];
		int[] framemodelP = getSimulatedFrame(request, "p_real_frame1", count);
		int[] framemodelB = getSimulatedFrame(request, "b_real_frame1", count);

		System.out.println("Congratulations！");
		int[] list = new int[count];

		int pStart = 0, bStart = 0;
		// 组合ibp帧
		for (int i = 0; i < count; i++) {
			if (i % 24 == 0) {
				list[i] = (int) framemodelI.get(i % 24).doubleValue();
			} else if (i % 24 == 1 || i % 24 == 5 || i % 24 == 9 || i % 24 == 13 || i % 24 == 17 || i % 24 == 21) {
				list[i] = framemodelP[pStart] * 100;
				pStart++;
			} else {
				list[i] = framemodelB[bStart] * 100;
				bStart++;
			}
		}

		return list;
	}

	/**
	 * 封装智能家居数据，传入count默认1024字节
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/smartHome", method = RequestMethod.GET)
	public Msg getHomeDataWithJSON(HttpServletRequest request,
								   @RequestParam(value = "count", defaultValue = "30000") Integer count) throws Exception {
		String readTxtPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/ModelData/access_intensity.txt");
//		count=getSections(ServletRequest request);
//		System.out.println(count);
		List<Double> list = SmartHome.smartHome(count,  readTxtPath);
		// p, b 帧除以100 I帧除以 1000
//		System.out.println(list.get(1));
//		System.out.println(list.size());
		return Msg.success().add("frames", list);
	}

	/**
	 * 封装远程检测数据，传入count默认1024字节
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/remoteDetection", method = RequestMethod.GET)
	public Msg getRemoteDataWithJSON(HttpServletRequest request,
									 @RequestParam(value = "count", defaultValue = "30000") Integer count) throws Exception {
		String readTxtPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/ModelData/access_intensity2.txt");
		List<Double> list = RemoteDetection.remoteDetection(count, readTxtPath);
		System.out.println(list.get(1));
		System.out.println(list.size());
		return Msg.success().add("frames", list);
	}

}