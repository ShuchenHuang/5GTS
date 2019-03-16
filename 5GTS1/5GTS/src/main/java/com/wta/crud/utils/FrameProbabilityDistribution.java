package com.wta.crud.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 概率密度函数数据生成类
 * @author Lee
 *
 */
public class FrameProbabilityDistribution {

	public static void main(String[] args) {

		Random rand = new Random();
		int[] a = new int[100];
		for (int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt(100) + 1;
		}
		double[][] b = new double[25][];
		b = statisticPD(a, 100, 5);
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j]);
				System.out.print("---->");
			}
			System.out.println();
		}

	}

	/**
	 * 统计序列的概率密度返回横纵坐标
	 * @param frames 序列
	 * @param framenum 序列长度，从外面计算一并传进来了
	 * @param intervalCount 区间个数
	 * @return
	 */
	public static double[][] statisticPD(int[] frames, int framenum, int intervalCount) {
		// 概率密度
		double Pd;
		
		// 区间上下界和中间值
		int upInterval, downInterval, middleValue;
		// 每个区间内的帧数量
		int count = 0;

		// 横纵坐标保存数组
		double[][] frameArray = new double[intervalCount][2];

		Arrays.sort(frames);

		int minFrame = frames[0];
		int maxFrame = frames[framenum - 1];
		// 区间宽度
		int interval = (maxFrame - minFrame) / intervalCount;

		System.out.println("Min=" + minFrame + " " + "Max=" + maxFrame);

		for (int k = 0; k < intervalCount; k++) {

			upInterval = minFrame + (k + 1) * interval - 1;
			downInterval = minFrame + k * interval;
			middleValue = downInterval + interval / 2; // 中点值（每一个横坐标）

			for (int i = 0; i < framenum; i++) {
				if (frames[i] < upInterval && frames[i] >= downInterval) {
					count++;
				}
			}
			Pd = (double) count / framenum / interval; // 纵坐标
			frameArray[k][0] = middleValue;
			frameArray[k][1] = Pd;
			count = 0;
		}

		return frameArray;
	}
}
