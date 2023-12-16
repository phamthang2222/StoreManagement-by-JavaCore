package vn.devpro.shopManagement.Update.category;

import java.util.Scanner;

public class Category {
	private int id;
	private String code;
	private String	name;
	static Scanner sc = new Scanner(System.in);
	
	public void display() {
		System.out.printf("%5d %-5s %-25s%n", this.id, this.code,
				this.name);
	}
	
	public void edit() {
		do {
			System.out.println("\t1.Sửa code");
			System.out.println("\t2.Sửa tên chủng loại");
			System.out.println("\t0.Quay lại");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1: 
				editCategoryCode();	
				break;
			case 2:
				editName();	
				break;

			case 0:
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
	
		}while(true);
	}
	
	public Category(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	public Category() {
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
	
	private void editCategoryCode() {
		System.out.print("\nNhập code: ");
		String code = sc.nextLine();
		
		if(CategoryManagement.findByCode(code) != -1) {
			System.out.println("Code đã tồn tại!");
			return;
		}
		this.setCode(code);
		System.out.println("-> Sửa thành công!");
	}
	private void editName() {
		System.out.print("\nNhập tên chủng loại: ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("Không được để trống tên chủng loại!");
			return;
		}
		if(CategoryManagement.findByName(name) != -1) {
			System.out.println("Chủng loại đã tồn tại!");
			return;
		}
		this.setName(name);
		System.out.println("-> Sửa thành công!");
	}
	
}	
