package vn.devpro.shopManagement.Update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.shopManagement.Update.category.CategoryManagement;


public class ProductManagement {
	public static int autoId = 1;
	
	static Scanner sc = new Scanner(System.in);
	
	public static ArrayList<Product> products = new ArrayList<Product>();
	
	public static void init() {
		products.add(new Product(autoId++, 1, "SDN3", "Áo phông", 250_000));
		products.add(new Product(autoId++, 1, "SHUE", "Mũ bảo hiểm", 250_000));
		products.add(new Product(autoId++, 1, "S34E", "Sơ mi Việt Tiến", 299_000));
		products.add(new Product(autoId++, 1, "4L43", "Mũ lưỡi chai", 34_000));
		products.add(new Product(autoId++, 2, "MNU3", "Váy dài", 350_000));
		products.add(new Product(autoId++, 2, "KJF4", "Áo dài", 550_000));
		products.add(new Product(autoId++, 2, "KMJ6", "Áo sơ mi", 550_000));
		products.add(new Product(autoId++, 3, "SE32", "Tất", 10_000));
		products.add(new Product(autoId++, 3, "DR33", "Mũ len", 60_000));
	}
	
	public static void execute() {
		do {
			System.out.println("\n--------------CẬP NHẬT THÔNG TIN SẢN PHẨM--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Hiển thị danh sách sản phẩm");
			System.out.println("\t2.Thêm mới sản phẩm");
			System.out.println("\t3.Sửa thông tin sản phẩm ");
			System.out.println("\t4.Xóa thông tin sản phẩm ");
			System.out.println("\t5.Sắp xếp danh sách");
			System.out.println("\t6.Tìm kiếm theo chủng loại");
			System.out.println("\t7.Tìm kiếm theo tên");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				display();
				break;// Hiển thị danh sách sản phẩm
			case 2:
				add();
				break;// Thêm mới sản phẩm 
			case 3: 
				edit();
				break;//Sửa thông tin sản phẩm 
			case 4: 
				remove();
				break;//Xóa thông tin sản phẩm 
			case 5: 
				sort();
				break;//Sắp xếp danh sách
			case 6:
				findByIdCategory();
				break;// tìm sp theo id chủng loại
			case 7:
				findByName();
				break; // tìm sp chứa chuỗi nhập vào
			case 0: 
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
		}while(true);
	}
	//case 1: hiển thị
	private static void display() {
		System.out.println("\n------------------DANH SÁCH SẢN PHẨM------------------");
		System.out.printf("%-2s %5s %-20s %-30s %-15s%n","ID","CODE","TÊN CHỦNG LOẠI","TÊN SẢN PHẨM",
				"ĐƠN GIÁ");
		for(Product product : products) {
			product.display();
		}
	}
	// case 2: thêm sản phẩm
	private static void add() {
		System.out.println("\n------------Thêm sản phẩm mới vào danh sách------------");
		System.out.print("\nNhập id chủng loại: ");
		int idCategory = Integer.parseInt(sc.nextLine());
		
		if(CategoryManagement.findById(idCategory) == -1) {
			System.out.println("\tId không tồn tại");
			return;
		}
		System.out.print("\nNhập code sản phẩm: ");
		String code = sc.nextLine();
		if( findProductByCode(code) != -1 ) {
			System.out.println("\tCode đã tồn tại");
			return;
		}
		System.out.print("\nNhập tên sản phẩm: ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		System.out.print("\nNhập giá sản phẩm: ");
		double price = Double.parseDouble(sc.nextLine());
		if(price < 0) {
			System.out.println("\tĐơn giá phải > 0!");
			return;
		}
		Product newProduct = new Product(autoId++, idCategory, code, name, price);
		products.add(newProduct);
		System.out.println("-> Thêm thành công");
	}
	
	//case 3: sửa sản phẩm
	private static void edit() {
		System.out.println("\n------------Sửa thông tin sản phẩm------------");
		System.out.print("\nNhập ID sản phẩm muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		int index= findProductByID(id);
		//kiem tra tinh hop le
		if(index == -1) {
			System.out.println("\tID không tồn tại");
			return;
		}
		products.get(index).edit();
		
	}

	//case 4: xóa 
	private static void remove() {
		System.out.println("\n------------Xóa thông tin sản phẩm------------");
		System.out.print("\nNhập ID sản phẩm muốn xóa: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findProductByID(id);
		
		if(index == -1) {
			System.out.println("\tID không tồn tại");
			return;
		}
		//xoa khoi danh sach
		products.remove(index);
		System.out.println("-> Xóa thành công!");
		
	}
	
	//case5: sắp xếp
	private static void sort() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
	//case 6: tim theo chung loại
	private static void findByIdCategory() {
		System.out.println("\n------------Tìm thông tin sản phẩm------------");
		System.out.print("\nNhập ID chủng loại: ");
		int idCategory = Integer.parseInt(sc.nextLine());
		
		if(CategoryManagement.findById(idCategory) == -1) {
			System.out.println("\tId không tồn tại");
			return;
		}
		
		//hien thi
		System.out.println("\n------------------DANH SÁCH SẢN PHẨM SAU TÌM KIẾM------------------");
		System.out.printf("%-2s %5s %-20s %-30s %-15s%n","ID","CODE","TÊN CHỦNG LOẠI","TÊN SẢN PHẨM",
				"ĐƠN GIÁ");
		for(Product product : products) {
			if(product.getCategoryId() == idCategory) {
				
				product.display();
			}
		}
		
	}
	
	//case 7: tim theo tên
	private static void findByName() {
		System.out.println("\n------------Tìm thông tin sản phẩm------------");
		System.out.print("\nNhập tên sản phẩm muốn tìm: ");
		String partOfName = sc.nextLine();

		//hien thi
		System.out.println("\n------------------DANH SÁCH SẢN PHẨM SAU TÌM KIẾM------------------");
		System.out.printf("%-2s %5s %-20s %-30s %-15s%n","ID","CODE","TÊN CHỦNG LOẠI","TÊN SẢN PHẨM",
				"ĐƠN GIÁ");
		for(Product product : products) {
			if(product.getName().trim().toLowerCase().contains(partOfName.toLowerCase()) ) {
				product.display();
			}
		}	
	}
	
	// hàm tìm kiếm tên sản phẩm có bị trùng hay ko.
	public static int findProductByName(String name) {
		for( int i = 0 ; i < products.size(); i++) {
			if(products.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	public static int findProductByID(int ID) {
		for( int i = 0 ; i < products.size(); i++) {
			if(products.get(i).getId() == ID) {
				return i;
			}
		}
		return -1;
	}
	public static int findProductByCode(String code) {
		for( int i = 0 ; i < products.size(); i++) {
			if(products.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}
	
	//lay 1 san pham theo id
	public static Product getProductById(int id) {
		for( Product product : products) {
			if(product.getId()== id) {
				return product;
			}
		}
		return new Product();
	}

}
