package vn.devpro.shopManagement.Update.customer;

import java.util.Scanner;


public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	static Scanner sc = new Scanner(System.in);
	
	public void display() {
		System.out.printf("%5d %-5s %-25s %-13s%n", this.id, this.code,this.name, this.mobile);
	}
	
	public void edit() {
		do {
			System.out.println("\t1.Sửa code");
			System.out.println("\t2.Sửa tên khách hàng");
			System.out.println("\t3.Sửa SĐT khách hàng");
			System.out.println("\t0.Quay lại");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1: 
				editCodeCustomer();
				break;
			case 2:
				editNameCustomer();
				break;
			case 3:
				editMobileCustomer();
				break;

			case 0:
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
	
		}while(true);
	}
	

	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
	}
	public Customer() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

	private void editCodeCustomer() {
		System.out.print("\nNhập code: ");
		String code = sc.nextLine();
		
		if(CustomerManagement.findCustomerByCode(code) != -1) {
			System.out.println("Code đã tồn tại!");
			return;
		}
		this.setCode(code);
		System.out.println("-> Sửa thành công!");
		
	}

	private void editNameCustomer() {
		System.out.print("\nNhập tên khách hàng: ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("Không được để trống tên khách hàng!");
			return;
		}
		this.setName(name);
		System.out.println("-> Sửa thành công!");
	}

	private void editMobileCustomer() {
		System.out.print("\nNhập SĐT khách hàng: ");
		String phone = sc.nextLine();
		if(phone.isEmpty()) {
			System.out.println("Không được để trống SĐT khách hàng!");
			return;
		}
		this.setMobile(phone);
		System.out.println("-> Sửa thành công!");
		
	}
}
