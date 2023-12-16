package vn.devpro.shopManagement.Update.product;

import java.util.Scanner;

import vn.devpro.shopManagement.Update.category.Category;
import vn.devpro.shopManagement.Update.category.CategoryManagement;

public class Product {
	private int id;
	private int categoryId;
	private String code;
	private String name;
	private double price;
	Scanner sc = new Scanner(System.in);
	
	public void display() {
		Category category = CategoryManagement.getCategory(categoryId);
		System.out.printf("%2d %5s %-20s %-30s %,15.2f%n",this.id,this.code,category.getName(),
				this.name, this.price);
	}
	public void edit() {
		do {
			System.out.println("\t1.Sửa chủng loại");
			System.out.println("\t2.Sửa code sản phẩm");
			System.out.println("\t3.Sửa tên sản phẩm");
			System.out.println("\t4.Sửa đơn giá");
			System.out.println("\t0.Quay lại");
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1: 
				editIdCaregory();
				break;
			case 2:
				editCode();
				break;
			case 3:
				editName();
				break;
			case 4:
				editPrice();
				break;
			case 0:
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}

		}while(true);
	}
	
	
	public Product(int id, int categoryId, String code, String name, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	private void editIdCaregory() {
		System.out.print("Nhập mã loại hàng: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		
		//kiem tra tinh hop le
		if((CategoryManagement.findById(categoryId)) == -1) {
			System.out.println("\t-Loại hàng không tồn tại");
			return;
		}
		this.setCategoryId(categoryId);
		System.out.println("-> Sửa thành công!");
		
	}
	private void editCode() {
		System.out.print("Nhập code sản phẩm: ");
		String code = sc.nextLine();
		
		//kiem tra tinh hop le
		if((ProductManagement.findProductByCode(code)) != -1) {
			System.out.println("\t-Code sản phẩm đã tồn tại");
			return;
		}
		this.setCode(code);
		
		System.out.println("-> Sửa thành công!");
		
	}
	private void editName() {
		System.out.print("Nhập tên sản phẩm: ");
		String name = sc.nextLine();
		
		this.setName(name);
		
		System.out.println("-> Sửa thành công!");
		
	}
	private void editPrice() {
		System.out.print("\nNhập giá sản phẩm: ");
		double price = Double.parseDouble(sc.nextLine());
		if(price < 0) {
			System.out.println("\tĐơn giá phải > 0!");
			return;
		}
		
		this.setCode(code);
		
		System.out.println("-> Sửa thành công!");	
	}
}
