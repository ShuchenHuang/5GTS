package com.wta.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wta.crud.bean.RealFrame;
import com.wta.crud.bean.RealFrameExample;
import com.wta.crud.bean.RealFrameExample.Criteria;
import com.wta.crud.dao.RealFrameMapper;

@Service
public class FrameService {

	@Autowired
	RealFrameMapper realFrameMapper;

	/**
	 * 获取某列帧数据
	 * 
	 * @param columnName
	 * @param count
	 * @return
	 */
	public int[] getColumn(String columnName, Integer count) {

		int[] frames = new int[count];

		RealFrameExample realFrameExample = new RealFrameExample();
		Criteria criteria = realFrameExample.createCriteria();

		if (columnName == "p_real_frame1")
			criteria.andPRealFrame1IsNotNull();
		if (columnName == "b_real_frame1")
			criteria.andBRealFrame1IsNotNull();
		if (columnName == "ipb_real_frame1")
			criteria.andIpbRealFrame1IsNotNull();

		// 默认按id 升序
		realFrameExample.setOrderByClause("id");
		List<RealFrame> realFrame = realFrameMapper.selectByExample(realFrameExample);

		for (int i = 0; i < realFrame.size(); i++) {
			if (columnName == "p_real_frame1")
				frames[i] = realFrame.get(i).getpRealFrame1();
			if (columnName == "b_real_frame1")
				frames[i] = realFrame.get(i).getbRealFrame1();
			if (columnName == "ipb_real_frame1")
				frames[i] = realFrame.get(i).getIpbRealFrame1();
		}

		return frames;
	}

	/**
	 * 从数据中获取 count 数量的帧
	 * 
	 * @param count
	 * @return
	 */
	public List<RealFrame> getFrame(Integer count) {

		RealFrameExample realFrameExample = new RealFrameExample();
		Criteria criteria = realFrameExample.createCriteria();
		criteria.andIdLessThanOrEqualTo(count);
		List<RealFrame> list = realFrameMapper.selectByExample(realFrameExample);

		return list;
	}

	/**
	 * 获取某列帧的数量
	 * 
	 * @param columnName
	 * @return
	 */
	public int getFrameCount(String columnName) {
		int count = realFrameMapper.selectDataCount(columnName);
		return count;
	}

	/**
	 * 获取某列帧的第一个元素
	 * 
	 * @param columnName
	 * @return
	 */
	public int getFirstFrame(String columnName) {
		RealFrameExample realFrameExample = new RealFrameExample();
		Criteria criteria = realFrameExample.createCriteria();
		criteria.andIdEqualTo(1);
		List<RealFrame> rf = realFrameMapper.selectByExample(realFrameExample);
		if (columnName == "p_real_frame1")
			return rf.get(0).getpRealFrame1()/100;
		else if (columnName == "b_real_frame1")
			return rf.get(0).getbRealFrame1()/100;
		else if (columnName == "i_real_frame1")
			return rf.get(0).getiRealFrame1();
		else
			return 0;
	}
}
