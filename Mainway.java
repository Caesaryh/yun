package ְ����Ϣ����;

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

	// ����Ա��Ϣ
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

	// Ա����Ϣ
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

	// ����Ա��֤
	public boolean cheakMan(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getId()) {
				countMan = i;
				System.out.println("id��ȷ");
				return true;
			}
		}
		System.out.println("id����");
		return false;
	}

	public boolean cheakPwd(String pwd) {
		if (pwd.equals(list.get(countMan).getPwd())) {
			System.out.println("������ȷ");
			return true;
		}
		System.out.println("�������");
		return false;
	}

	// �˵�
	public void menu() {
		System.out.println("-------��ӭ����ְ����Ϣ����ϵͳ-------");
		System.out.println("---------------1.��ѯ---------------");
		System.out.println("---------------2.����---------------");
		System.out.println("---------------3.ɾ��---------------");
		System.out.println("---------------4.�޸�---------------");
		System.out.println("---------------5.���---------------");
		System.out.println("---------------6.�˳�---------------");
		System.out.println("----------�����������������----------");
		choose();

	}

	public void choose() {
		count = in.nextInt();
		switch (count) {

		case 1:
			System.out.println("----��ѯ��ʽ----");
			System.out.println("-----1.����-----");
			System.out.println("-----2.����-----");
			count = in.nextInt();
			query(count);
			break;
		case 2:
			System.out.println("��н�ʽ���");
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
			System.out.println("����������");
			choose();
			break;
		}
	}

	public void query(int count) {
		if (count == 1) {
			System.out.println("---�����빤��---");
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
				System.out.println("���Ų�����");
				query(1);
			}
		}
		if (count == 2) {
			System.out.println("---����������---");
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
				System.out.println("������������");
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
		System.out.println("������Ҫɾ���Ĺ��ţ�");
		count = in.nextInt();
		boolean flag = true;
		for(int i=0;i<list1.size();i++) {
			if(list1.get(i).getId()==count) {
				list1.remove(i);
				cover();
				System.out.println("ɾ���ɹ�");
				flag = false;
				menu();
				break;
			}
		}
		if(flag) {
			System.out.println("����ɾ���Ĺ��Ų�����");
			dele();
		}
	}

	public void revise() {
		System.out.println("�����������޸Ĺ��ţ�");
		count = in.nextInt();
		boolean flag = true;
		for(int i = 0;i<list1.size();i++) {
			if(count == list1.get(i).getId()) {
				System.out.println("------��ѡ���޸�����------");
				System.out.println("---1.����---2.�Ա�---");
				System.out.println("---3.��������---4.ѧ��---");
				System.out.println("---5.ְλ---6.סַ---");
				System.out.println("---7.��ϵ��ʽ---8.����---");
				System.out.println("------9.����------");
				reviseContent(i);
				flag = false;
				menu();
				break;
			}
		}
		if(flag) {
			System.out.println("���Ų�����");
			revise();
		}
	}
	
	public void reviseContent(int i) {
		System.out.println("�������޸����ݵ�ѡ�");
		int count = in.nextInt();
		if(count >= 1 && count <= 8) {
			System.out.println("�������޸����ݣ�");
			switch(count) {
			case 1:
				System.out.println("�������޸�������");
				list1.get(i).setName(in.next());
				break;
			case 2:
				System.out.println("�������޸��Ա�");
				list1.get(i).setSex(in.next());
				break;
			case 3:
				System.out.println("�������޸ĳ�������");
				list1.get(i).setBirth(in.next());
				break;
			case 4:
				System.out.println("�������޸�ѧ��");
				list1.get(i).setEdu(in.next());
				break;
			case 5:
				System.out.println("�������޸�ְλ");
				list1.get(i).setPost(in.next());
				break;
			case 6:
				System.out.println("�������޸�סַ");
				list1.get(i).setAddress(in.next());
				break;
			case 7:
				System.out.println("�������޸���ϵ��ʽ");
				list1.get(i).setTel(in.next());
				break;
			case 8:
				System.out.println("�������޸�н��");
				list1.get(i).setSalary(in.nextInt());
				break;
			case 9:
				menu();break;
			}
			if(count!=9)
				System.out.println("�޸ĳɹ�");
		}else {
			System.out.println("ѡ�����");
			reviseContent(i);
		}
	}

	public void add() {
		Employee emp = new Employee();
		System.out.println("���ţ�");
		while(true) {
			emp.setId(in.nextInt());
			boolean flag = false;
			for(int i=0;i<list1.size();i++) {
				if(emp.getId() == list1.get(i).getId()) {
					flag = true;
				}
			}
			if(flag) {
				System.out.println(emp.getId()+"�ù����Ѵ��ڣ����������룺");
			}else {
				break;
			}
		}
		
		System.out.println("������");
		emp.setName(in.next());
		System.out.println("�Ա�");
		emp.setSex(in.next());
		System.out.println("�������ڣ�");
		emp.setBirth(in.next());
		System.out.println("ѧ����");
		emp.setEdu(in.next());
		System.out.println("ְ��");
		emp.setPost(in.next());
		System.out.println("סַ��");
		emp.setAddress(in.next());
		System.out.println("��ϵ��ʽ��");
		emp.setTel(in.next());
		System.out.println("н�ʣ�");
		emp.setSalary(in.nextInt());
		list1.add(emp);
		cover();
		System.out.println("��ӳɹ�");
		menu();

	}

	public void quit() {
		System.out.println("�˳��ɹ�");
	}

	public void cover()  {
		// Data�ļ�����
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
