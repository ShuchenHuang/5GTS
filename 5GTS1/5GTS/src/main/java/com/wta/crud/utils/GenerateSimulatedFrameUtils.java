package com.wta.crud.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 生成模拟帧数据类
 * @author Lee
 *
 */
public class GenerateSimulatedFrameUtils {

	public static void main(String[] args) throws Exception {

		String readTxtPath = System.getProperty("user.dir")+"\\src\\main\\resources\\ModelData\\pmatrix1.txt";
		int[] framemodel = new int[8929];
		
		long startTime = System.currentTimeMillis();

		framemodel = generatedSequence(readTxtPath, 3354, 926);
		printArray(framemodel);

//		System.out.println("Congratulations！");

		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;
//		System.out.println("seconds: " + seconds);
	}

	/**
	 * 
	 * @param 帧序列长度
	 *            framenum = 4000
	 * @param 首个帧大小
	 *            framenow = 300
	 * @return
	 * @throws Exception
	 */
	public static int[] generatedSequence(String readTxtPath, int framenum, int framenow) throws Exception {

		// 定义一个 1*4000 的数组作为帧序列输出，实际视频帧座位预测第一帧的大小
		int[] framemodel = new int[framenum];
		for (int i = 1; i < framenum; i++) {
			framemodel[i] = 1;
		}
		framemodel[0] = framenow;
		framenow = framenow;

		// 从 .txt 文件中获取转移矩阵
		double[][] arrayP = creatArray(readTxtPath);

		// 得到转移矩阵的 宽度和高度
		int PLength = arrayP.length;

		double[] prow = new double[PLength];

		int n = 1;
		while (n < framenum) {
			// 生成(0,1) 之间随机数
			double u = nextDouble(0, 1);

			// 取得当前状态的转移概率分布，逐个累加
			for (int index = 0; index < PLength; index++) {
				prow[index] = arrayP[framenow][index];
			}

			// cumsum 是按行累加
			for (int index = 1; index < PLength; index++) {
				prow[index] = prow[index] + prow[index - 1];
			}

			for (int j = 0; j < PLength; j++) {
				// 当随机数小于某个累加概率，取此状态作为当前输出和当前状态
				if (prow[j] > u) {
					framemodel[n] = j;
					framenow = j;
					break;
				}
			}
			n = n + 1;
		}

		return framemodel;
	}

	/**
	 * 生成max到min范围的浮点数
	 */
	public static double nextDouble(final double min, final double max) {
		return min + ((max - min) * new Random().nextDouble());
	}

	/**
	 * 读取文件，返回一个一维数组
	 * 
	 * @param filepath
	 * @param r
	 * @return
	 */
	public static double[] creatArray(String filepath, int r) {

		return null;
	}

	/**
	 * 读取文件，返回一个二维数组
	 * 
	 * @param filepath
	 *            r为行数，c为列数
	 * @return
	 * @throws Exception
	 */
	public static double[][] creatArray(String filepath) throws Exception {

		InputStreamReader read = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(read);

		// 一行数据和行数
		String line = null;
		int row = readFileCount(filepath);

		// 建立数组存储读到的矩阵
		double[][] arr = new double[row][row];

		// 行数清零
		row = 0;

		// 逐行读取
		while ((line = bufferedReader.readLine()) != null) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++) {
				arr[row][i] = Double.parseDouble(temp[i]);
			}
			row++;
		}
		bufferedReader.close();
		return arr;
	}

	/**
	 * 将二维数组写出到文件
	 * 
	 * @param readTxtPath
	 * @param writeTxtPath
	 * @throws Exception
	 */
	public static void writeToTxt(String readTxtPath, String writeTxtPath, double[][] arr2) throws Exception {

		arr2 = creatArray(readTxtPath);

		// 存放数据的文件 writeTxtPath
		File file = new File("C:\\Users\\Lee\\Desktop\\array.txt");
		FileWriter out = new FileWriter(file);

		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				out.write(arr2[i][j] + "\t");
			}
			out.write("\r\n");
		}
		out.close();
	}

	/**
	 * 打印二维 double 数组
	 * 
	 * @param array
	 */
	public static void printArray(double array[][]) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 打印一维int数组
	 * 
	 * @param array
	 */
	public static void printArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
	}

	/**
	 * 打印一维double 数组
	 * 
	 * @param array
	 */
	public static void printArray(double array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
	}

	/**
	 * 读取文件的行数
	 * 
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public static int readFileCount(String filepath) throws Exception {
		InputStreamReader read = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(read);

		// 一行数据和行数
		String line = null;
		int row = 0;

		// 读取矩阵行数
		while ((line = bufferedReader.readLine()) != null) {
			row++;
		}

		bufferedReader.close();
		return row;
	}

}
