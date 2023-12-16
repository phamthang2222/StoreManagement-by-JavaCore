package vn.devpro.shopManagement.Update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CustomerManagement {
public static int autoId = 1 ;
	
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	static Scanner sc = new Scanner(System.in);
	public static void init() {
		customers.add(new Customer(autoId++, "KH31", "Nguyễn Văn Sáng", "+8433221155"));
		customers.add(new Customer(autoId++, "KH11", "Phạm Văn Huy", "+8433231155"));
		customers.add(new Customer(autoId++, "KH67", "Lương Thị Hòa", "+8487967355"));
		customers.add(new Customer(autoId++, "KH64", "Tống Minh Giang", "+8416573481"));
		customers.add(new Customer(autoId++, "KH89", "Huê Soong Lử", "+8490874298"));

	}
	public static void execute() {
		
		do {
			System.out.println("\n--------------CẬP NHẬT THÔNG TIN KHÁCH HÀNG--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Hiển thị danh sách khách hàng");
			System.out.println("\t2.Thêm mới khách hàng hóa");
			System.out.println("\t3.Sửa thông tin khách hàng hóa");
			System.out.println("\t4.Xóa thông tin khách hàng hóa");
			System.out.println("\t5.Sắp xếp danh sách");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				display();
				break;// Hiển thị danh sách khách hàng
			case 2:
				add();
				break;// Thêm mới khách hàng hóa
			case 3: 
				edit();
				break;//Sửa thông tin khách hàng hóa
			case 4: 
				remove();
				break;//Xóa thông tin khách hàng hóa
			case 5: 
				sort();
				break;//Sắp xếp danh sách
				
			case 0: 
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
		}while(true);
	}
	private static void display() {
		
		System.out.println("\n------------------DANH SÁCH KHÁCH HÀNG------------------");
		System.out.printf("%-5s %-5s %-25s %-13s%n", "ID", "CODE","TÊN KHÁCH HÀNG", "SĐT");
		for(Customer customers : customers) {
			customers.display();
		}
		
	}
	private static void add() {
		System.out.println("\n------------Thêm khách hàng mới vào danh sách------------");
		System.out.print("\nNhập code khách hàng mới: ");
		String code = sc.nextLine();
		
		if(findCustomerByCode(code) != -1) {
			System.out.println("\tĐã tồn tại code này!");
			return;
		}
		
		System.out.print("\nNhập tên khách hàng mới: ");
		String name = sc.nextLine();
		System.out.print("\nNhập SĐT khách hàng mới: ");
		String phone = sc.nextLine();
		
		Customer newCustomer = new Customer(autoId++, code, name, phone);
		customers.add(newCustomer);
		System.out.println("-> Thêm thành công!");
		
		
	}
	private static void edit() {
		System.out.println("\n------------Sửa thông tin khách hàng------------");
		System.out.print("\nNhập ID khách hàng muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		int index= findCustomerByID(id);
		
		//kiem tra tinh hop le
		if(index == -1) {
			System.out.println("\t-ID không tồn tại");
			return;
		}
		customers.get(index).edit();
	}
	private static void remove() {
		System.out.println("\n------------Xóa thông tin khách hàng------------");
		System.out.print("\nNhập ID khách hàng muốn xóa: ");
		
		int id = Integer.parseInt(sc.nextLine());
		int index = findCustomerByID(id);
		
		if(index == -1) {
			System.out.println("\t-ID không tồn tại");
			return;
		}
		//xoa khoi danh sach
		customers.remove(index);
		System.out.println("-> Xóa thành công!");
		
	}
	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>(){
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	public static int findCustomerByName(String name) {
		for( int i = 0 ; i < customers.size(); i++) {
			if(customers.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	public static int findCustomerByCode(String code) {
		for( int i = 0 ; i < customers.size(); i++) {
			if(customers.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int findCustomerByID(int ID) {
		for( int i = 0 ; i < customers.size(); i++) {
			if(customers.get(i).getId() == ID) {
				return i;
			}
		}
		return -1;
	}
	
	public static Customer findCustomerById(int ID) {
		for( int i = 0 ; i < customers.size(); i++) {
			if(customers.get(i).getId() == ID) {
				return customers.get(i) ;
			}
		}
		return new Customer() ;
	}
}
