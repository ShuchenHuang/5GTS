package com.wta.crud.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoteDetection {

	public static void main(String[] args) {

	}
	/**
	 * 返回远程检测业务序列
	 * @param N
	 * @param dataSize
	 * @return
	 * @throws Exception
	 */
	public static List<Double> remoteDetection(int N,  String filepath) throws Exception {
		List<Double> list = creatArray(filepath);
		for (int i = 0 ; i < list.size() ; i++) {
			int dataSize = roundSize(50, 10);
			double temp = list.get(i) * N * dataSize;
			list.set(i, temp);
		}
		return list;		
	}
	
	private static int roundSize(int min, int max){
        int size = new Random().nextInt((max-min)+min);
        return size;
	}
	
	/**
	 * 读文件存入list
	 * @param filepath
	 * @return
	 * @throws Exception
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
}
