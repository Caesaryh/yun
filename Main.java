package 职工信息管理;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		int id ;
		while(true) {
			
			Mainway way = new Mainway();
			way.readMan();
			
			while(true) {
				System.out.println("请输入管理员id：");
				id = in.nextInt();
				if(way.cheakMan(id)) {
					break;
				}
			}
			while(true) {
				System.out.println("请输入密码：");
				String pwd = in.next();
				if(way.cheakPwd(pwd)) {
					break;
				}
			}
			
			way.readEmp();
			
			way.menu();
			
		}

	}

}
