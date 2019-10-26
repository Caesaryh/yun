package 职工信息管理;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.CollationKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Mainway implements java.util.Comparator<Employee>{
	int countMan = -1;
	int countEmp = -1;

	Scanner in = new Scanner(System.in);
	int count;

	// 管理员信息
	List<Mananger> list = new ArrayList<>();

	public void readMan() {
		try {
			Scanner inn = new Scanner(new FileInputStream("Manger.txt"));
			while (inn.hasNextLine()) {
				String s = inn.nextLine();
				String[] arr = s.split("#");
				list.add(new Mananger(Integer.parseInt(arr[0]), arr[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 员工信息
	List<Employee> list1 = new ArrayList<>();

	public void readEmp() {
		try {
			Scanner inn = new Scanner(new FileInputStream("Employee.txt"));
			while (inn.hasNextLine()) {
				String s = inn.nextLine();
				String[] arr1 = s.split("#");
				list1.add(new Employee(Integer.parseInt(arr1[0]), arr1[1], arr1[2], arr1[3], arr1[4], arr1[5], arr1[6],
						arr1[7], Integer.parseInt(arr1[8])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 管理员验证
	public boolean cheakMan(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getId()) {
				countMan = i;
				System.out.println("id正确");
				return true;
			}
		}
		System.out.println("id错误");
		return false;
	}

	public boolean cheakPwd(String pwd) {
		if (pwd.equals(list.get(countMan).getPwd())) {
			System.out.println("密码正确");
			return true;
		}
		System.out.println("密码错误");
		return false;
	}

	// 菜单
	public void menu() {
		System.out.println("-------欢迎进入职工信息管理系统-------");
		System.out.println("---------------1.查询---------------");
		System.out.println("---------------2.排序---------------");
		System.out.println("---------------3.删除---------------");
		System.out.println("---------------4.修改---------------");
		System.out.println("---------------5.添加---------------");
		System.out.println("---------------6.退出---------------");
		System.out.println("----------请输入所作操作序号----------");
		choose();

	}

	public void choose() {
		count = in.nextInt();
		switch (count) {

		case 1:
			System.out.println("----查询方式----");
			System.out.println("-----1.工号-----");
			System.out.println("-----2.姓名-----");
			count = in.nextInt();
			query(count);
			break;
		case 2:
			System.out.println("按薪资降序");
			sort();
			break;
		case 3:
			dele();
			break;
		case 4:
			revise();
			break;
		case 5:
			add();
			break;
		case 6:
			quit();
			break;
		default:
			System.out.println("请重新输入");
			choose();
			break;
		}
	}

	public void query(int count) {
		if (count == 1) {
			System.out.println("---请输入工号---");
			int id = in.nextInt();
			boolean flag = true;
			for (int i = 0; i < list1.size(); i++) {
				if (id == list1.get(i).getId()) {
					System.out.println(list1.get(i).toString() + "\n");
					menu();
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("工号不存在");
				query(1);
			}
		}
		if (count == 2) {
			System.out.println("---请输入姓名---");
			String name = in.next();
			boolean flag = true;
			for (int i = 0; i < list1.size(); i++) {
				if (name.equals(list1.get(i).getId())) {
					System.out.println(list1.get(i).toString() + "\n");
					menu();
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("该姓名不存在");
				query(2);
			}
		}

	}

	public void sort() {
		Collections.sort(list1, new Mainway());
		for (Employee emp : list1) {
			System.out.println(emp);
		}
		menu();

	}
	public int compare(Employee o1, Employee o2) {
		if(o1.getSalary()>o2.getSalary()) 
			return -1;
		if(o1.getSalary()<o2.getSalary())
			return 1;
		return 0;
	}

	public void dele() {
		System.out.println("请输入要删除的工号：");
		count = in.nextInt();
		boolean flag = true;
		for(int i=0;i<list1.size();i++) {
			if(list1.get(i).getId()==count) {
				list1.remove(i);
				cover();
				System.out.println("删除成功");
				flag = false;
				menu();
				break;
			}
		}
		if(flag) {
			System.out.println("所需删除的工号不存在");
			dele();
		}
	}

	public void revise() {
		System.out.println("请输入所需修改工号：");
		count = in.nextInt();
		boolean flag = true;
		for(int i = 0;i<list1.size();i++) {
			if(count == list1.get(i).getId()) {
				System.out.println("------请选择修改内容------");
				System.out.println("---1.姓名---2.性别---");
				System.out.println("---3.出生日期---4.学历---");
				System.out.println("---5.职位---6.住址---");
				System.out.println("---7.联系方式---8.工资---");
				System.out.println("------9.返回------");
				reviseContent(i);
				flag = false;
				menu();
				break;
			}
		}
		if(flag) {
			System.out.println("工号不存在");
			revise();
		}
	}
	
	public void reviseContent(int i) {
		System.out.println("请输入修改内容的选项：");
		int count = in.nextInt();
		if(count >= 1 && count <= 8) {
			System.out.println("请输入修改内容：");
			switch(count) {
			case 1:
				System.out.println("请输入修改姓名：");
				list1.get(i).setName(in.next());
				break;
			case 2:
				System.out.println("请输入修改性别");
				list1.get(i).setSex(in.next());
				break;
			case 3:
				System.out.println("请输入修改出生日期");
				list1.get(i).setBirth(in.next());
				break;
			case 4:
				System.out.println("请输入修改学历");
				list1.get(i).setEdu(in.next());
				break;
			case 5:
				System.out.println("请输入修改职位");
				list1.get(i).setPost(in.next());
				break;
			case 6:
				System.out.println("请输入修改住址");
				list1.get(i).setAddress(in.next());
				break;
			case 7:
				System.out.println("请输入修改联系方式");
				list1.get(i).setTel(in.next());
				break;
			case 8:
				System.out.println("请输入修改薪资");
				list1.get(i).setSalary(in.nextInt());
				break;
			case 9:
				menu();break;
			}
			if(count!=9)
				System.out.println("修改成功");
		}else {
			System.out.println("选项不存在");
			reviseContent(i);
		}
	}

	public void add() {
		Employee emp = new Employee();
		System.out.println("工号：");
		while(true) {
			emp.setId(in.nextInt());
			boolean flag = false;
			for(int i=0;i<list1.size();i++) {
				if(emp.getId() == list1.get(i).getId()) {
					flag = true;
				}
			}
			if(flag) {
				System.out.println(emp.getId()+"该工号已存在，请重新输入：");
			}else {
				break;
			}
		}
		
		System.out.println("姓名：");
		emp.setName(in.next());
		System.out.println("性别：");
		emp.setSex(in.next());
		System.out.println("出生日期：");
		emp.setBirth(in.next());
		System.out.println("学历：");
		emp.setEdu(in.next());
		System.out.println("职务：");
		emp.setPost(in.next());
		System.out.println("住址：");
		emp.setAddress(in.next());
		System.out.println("联系方式：");
		emp.setTel(in.next());
		System.out.println("薪资：");
		emp.setSalary(in.nextInt());
		list1.add(emp);
		cover();
		System.out.println("添加成功");
		menu();

	}

	public void quit() {
		System.out.println("退出成功");
	}

	public void cover()  {
		// Data文件覆盖
		BufferedWriter out1;
		try {
			out1 = new BufferedWriter(new FileWriter("Employee.txt"));
			for (int i = 0; i < list1.size(); i++) {
			out1.append(list1.get(i).getId() + "#" + list1.get(i).getName() + "#" + list1.get(i).getSex() + "#"
					+ list1.get(i).getBirth() + "#" + list1.get(i).getEdu() + "#" + list1.get(i).getPost() + "#"
					+ list1.get(i).getAddress() + "#" + list1.get(i).getTel() + "#" + list1.get(i).getSalary() + "\n");
		}
		out1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
