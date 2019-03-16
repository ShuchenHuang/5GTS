package com.wta.crud.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wta.crud.bean.RealFrame;
import com.wta.crud.bean.RealFrameExample;
import com.wta.crud.bean.RealFrameExample.Criteria;
import com.wta.crud.dao.RealFrameMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	RealFrameMapper realFrameMapper;	




	@Test
	public void testFrameSelect() {
		RealFrame selectByPrimaryKey = realFrameMapper.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey);
	}

//	@Test
	public void testFrameSelectByExample() {

		RealFrameExample RealFrameExample = new RealFrameExample();
		Criteria criteria = RealFrameExample.createCriteria();
		criteria.andIdLessThan(5);

		List<RealFrame> listTeammembers = realFrameMapper.selectByExample(RealFrameExample);
		for (RealFrame rf : listTeammembers) {
			System.out.println(rf);
		}
	}

}
