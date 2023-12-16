package vn.devpro.shopManagement.Update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class CategoryManagement {
	public static int autoId = 1 ;
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Category> categories = new ArrayList<Category>();
	
	public static void init() {
		categories.add(new Category(autoId++, "MH32", "Nam"));
		categories.add(new Category(autoId++, "MRT2", "Nữ"));
		categories.add(new Category(autoId++, "KS12", "Trẻ em"));
	}
	
	public static void excute() {
		do {
			System.out.println("\n--------------CẬP NHẬT THÔNG TIN CHỦNG LOẠI--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Hiển thị danh sách chủng loại");
			System.out.println("\t2.Thêm mới chủng loại");
			System.out.println("\t3.Sửa thông tin chủng loại");
			System.out.println("\t4.Xóa thông tin chủng loại");
			System.out.println("\t5.Sắp xếp danh sách");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				display();
				break;// Hiển thị danh sách chủng loại
			case 2:
				add();
				break;// Thêm mới loại chủng loại
			case 3: 
				edit();
				break;//Sửa thông tin loại chủng loại
			case 4: 
				remove();
				break;//Xóa thông tin loại chủng loại
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
		System.out.println("\n------------------DANH SÁCH CHỦNG LOẠI------------------");
		System.out.printf("%5s %-5s %-25s%n","ID","CODE","CHỦNG LOẠI" );
		for(Category category : categories) {
			category.display();
		}
		
	}

	private static void add() {
		System.out.println("\n------------Thêm chủng loại mới vào danh sách------------");
		System.out.print("\nNhập code chủng loại mới: ");
		String code = sc.nextLine();
		if(code.isEmpty()) {
			System.out.println("Không được để trống mã code!");
			return;
		}
		if(findByCode(code) != -1) {
			System.out.println("Mã code đã tồn tại!");
			return;
		}
		
		System.out.print("\nNhập tên chủng loại mới: ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("Không được để trống tên chủng loại!");
			return;
		}
		if(findByName(name) != -1) {
			System.out.println("Tên chủng loại đã tồn tại!");
			return;
		}
		
		Category newNategory = new Category(autoId++, code, name);
		categories.add(newNategory);
		System.out.println("\nThêm thành công!");
		
	}

	private static void edit() {
		System.out.println("\n------------Sửa chủng loại ------------");
		System.out.print("\nNhập ID chủng loại muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findById(id);
		if(index == -1) {
			System.out.println("ID không tồn tại!");
			return;
		}
		categories.get(index).edit();
		
	}

	private static void remove() {
		System.out.println("\n------------Xóa thông tin chủng loại------------");
		System.out.print("\nNhập ID chủng loại muốn xóa: ");
		
		int id = Integer.parseInt(sc.nextLine());
		int index = findById(id);
		
		if( index == -1) {
			System.out.println("\t-ID không tồn tại");
			return;
		}
		// xoa khoi danh sach
		categories.remove(index);
		System.out.println("\tXóa thành công!");
		
	}

	private static void sort() {
		Collections.sort(categories,new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		System.out.println("-> Sắp xếp thành công!");
		
	}
	
	public static int findByName(String name) {
		for( int i = 0 ; i < categories.size(); i++) {
			if(categories.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	public static int findByCode(String code) {
		for( int i = 0 ; i < categories.size(); i++) {
			if(categories.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}
	public static int findById(int  id) {
		for( int i = 0 ; i < categories.size(); i++) {
			if(categories.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	public static Category getCategory(int id) {
		for(Category category: categories) {
			if(category.getId() == id ) {
				return category;
			}
		}
		return new Category();
		
	}
	
}
