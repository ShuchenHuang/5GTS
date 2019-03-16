package com.wta.crud.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomAdaptor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 * I帧生成类，使用小波变换算法
 * 注：I帧生成算法从 matlab 代码中复写过来，具体步骤得含义需参阅 matlab 代码
 * @author Lee
 *
 */
public class GenerateSimulatedFrameIUtils {
	
	
	public static void main(String[] args) throws Exception {
		String readTxtPath = "C:\\Users\\Lee\\Desktop\\matlabA\\actual_coef.txt";
		System.out.println(waveletReconstruction(readTxtPath));
	}
	

	/**
	 * 小波重构I帧序列
	 * @return
	 * @throws Exception
	 */
	public static List<Double> waveletReconstruction(String readTxtPath) throws Exception {
		int[] l = new int[] { 83, 83, 166, 332, 663 };
		double[] lpr = new double[] { 0.7071, 0.7071 };
		double[] hpr = new double[] { 0.7071, -0.7071 };
		double[] model_a = generateWaveletParamA();
		double[] model_all_d = generateWaveletParamD(readTxtPath);
		double[] c = new double[model_all_d.length];

		for (int i = 0; i < model_a.length; i++)
			c[i] = model_a[i];

		for (int i = 0; i < model_all_d.length - model_a.length; i++) {
			c[model_a.length + i] = model_all_d[model_a.length + i];
		}

		List<Double> cA = new ArrayList<>();
		for (int i = 0; i < l[0]; i++) {
			cA.add(i, c[i]);
		}

		List<Double> cvl = new ArrayList<>();
		List<Double> cvh = new ArrayList<>();

		for (int i = 0; i < l.length - 2; i++) {
			// 对平均部分系数进行上抽样
			double[] upl = upsampling(cA);
			cvl = convolution(upl, lpr);

			for (int j = 0; j < cvl.size() - 2; j++) {
				cvl.set(j, cvl.get(j + 1));
			}

			int cvl_size = cvl.size();
			for (int j = cvl_size; j > cvl_size - 2; j--) {
				cvl.remove(j - 1);
			}

			int st = 0;
			for (int j = 0; j <= i; j++) {
				st = st + l[j];
			}
			st++;
			int ed = st + l[i + 1] - 1;

			// 对细节部分系数进行上抽样
			List<Double> cD = new ArrayList<>();
			for (int j = 0; j < ed - st + 1; j++) {
				cD.add(j, c[j + st - 1]);
			}
			double[] uph = upsampling(cD);
			cvh = convolution(uph, hpr);

			for (int j = 0; j < cvh.size() - 2; j++) {
				cvh.set(j, cvh.get(j + 1));
			}
			int cvh_size = cvh.size();
			for (int j = cvh_size; j > cvh_size - 2; j--) {
				cvh.remove(j - 1);
			}

			cA.clear();
			for (int j = 0; j < cvh.size(); j++) {
				cA.add(j, cvl.get(j) + cvh.get(j));
			}
		}
		cA.remove(0);
		return cA;
	}

	/**
	 * 获得 小波系数a， 符合gamma 分布
	 * 
	 * @return
	 * @throws Exception
	 */
	public static double[] generateWaveletParamA() throws Exception {

		int[] coefs_longs = new int[] { 83, 83, 166, 332, 663 };
		int a_len = coefs_longs[0];
		double[] model_a = new double[a_len];

		// k(shape) = 63.3021 θ(scale)= 9.342441568987151e+03
		GammaDistribution gamma = new GammaDistribution(63.3021, 9342.4415689871);

		for (int i = 0; i < a_len; i++)
			model_a[i] = gamma.sample();

		return model_a;
	}

	/**
	 * 产生小波系数d，符合拉普拉斯分布
	 * 
	 * @return
	 * @throws Exception
	 */
	public static double[] generateWaveletParamD(String readTxtPath) throws Exception {
		List<Double> actual_coefs = creatArray(readTxtPath);

		int[] coefs_longs = new int[] { 83, 83, 166, 332, 663 };
		double[] model_a = new double[coefs_longs[0]];

		// k(shape) = 63.3021 θ(scale)= 9.342441568987151e+03
		RandomDataGenerator random = new RandomDataGenerator();

		// GammaDistribution gamma = new GammaDistribution(63.3021, 9342.4415689871);
		for (int i = 0; i < coefs_longs[0]; i++)
			model_a[i] = random.nextGamma(63.3021, 9342.4415689871);

		int coefs_len = actual_coefs.size();
		double[] model_all_d = new double[coefs_len];

		int coef_start = coefs_longs[0] + 1;
		int coef_end = coef_start + coefs_longs[1] - 1;

		double[] d = new double[coefs_len];
		for (int i = 0; i < coef_end - coef_start + 1; i++) {
			d[i] = actual_coefs.get(coef_start + i - 1);
		}
		// 声明方差
		Variance xvar = new Variance();
		double b = xvar.evaluate(d);
		b = Math.sqrt(b / 2);

		RandomAdaptor r = new RandomAdaptor(new JDKRandomGenerator());

		double a[] = new double[d.length];

		for (int i = 0; i < d.length; i++) {
			a[i] = r.nextDouble() - 0.5;
			a[i] = a[i] > 0 ? a[i] = 1.0 : a[i] == 0 ? a[i] = 0.0 : -1.0;
		}
		for (int i = 0; i < d.length; i++) {
			a[i] = a[i] * (Math.log(1 - 2 * Math.abs(r.nextDouble() - 0.5)));
		}
		for (int i = 0; i < coef_end - coef_start + 1; i++) {
			model_all_d[coef_start + i - 1] = a[i];
		}

		for (int i = 2; i < 4; i++) {
			coef_start = coef_end + 1;
			coef_end = coef_start + coefs_longs[i] - 1;
			for (int k = 0; k < coef_end - coef_start + 1; k++) {
				d[k] = actual_coefs.get(coef_start + k - 1);
			}
			b = xvar.evaluate(d);
			b = Math.sqrt(b / 2);

			for (int k = 0; k < d.length; k++) {
				a[k] = r.nextDouble() - 0.5;
				a[k] = a[k] > 0 ? a[k] = 1.0 : a[k] == 0 ? a[k] = 0.0 : -1.0;
			}
			for (int k = 0; k < d.length; k++) {
				a[k] = a[k] * (Math.log(1 - 2 * Math.abs(r.nextDouble() - 0.5)));
			}
			for (int k = 0; k < coef_end - coef_start + 1; k++) {
				model_all_d[coef_start + k - 1] = a[k];
			}
		}

		return model_all_d;
	}

	/**
	 * 读取文件，返回一个一维数组
	 * 
	 * @param filepath
	 * @param r
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private static List<Double> creatArray(String filepath) throws Exception {
		InputStreamReader read = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(read);

		String line = null;
		List<Double> list = new ArrayList<>();
		// 逐行读取
		while ((line = bufferedReader.readLine()) != null) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++) {
				list.add(Double.parseDouble(temp[i]));
			}
		}
		bufferedReader.close();
		return list;
	}

	/**
	 * 上采样
	 * 
	 * @param cA
	 * @return
	 */
	private static double[] upsampling(List<Double> x) {
		int N = x.size();
		int M = 2 * N + 1;
		double[] y = new double[M];

		for (int i = 0; i < N; i++) {
			y[2 * i + 1] = x.get(i);
		}

		return y;
	}

	/**
	 * 一维卷积
	 * 
	 * @param fx
	 * @param gx
	 * @return
	 */
	private static List<Double> convolution(double[] fx, double[] gx) {
		int n = fx.length;
		int b = gx.length;
		List<Double> out = new ArrayList<>();
		for (int i = 0; i < n + b - 1; i++) {
			out.add(i, 0.0);
		}
		for (int i = 0; i < n + b - 1; i++) {
			for (int k = 0; k < b; k++) {
				if ((i - k) >= 0 && (i - k) < n)
					out.set(i, out.get(i) + fx[i - k] * gx[k]);
			}
		}

		return out;
	}

	
}
