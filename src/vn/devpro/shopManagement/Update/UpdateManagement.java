package vn.devpro.shopManagement.Update;

import java.util.Scanner;

import vn.devpro.shopManagement.Update.category.CategoryManagement;
import vn.devpro.shopManagement.Update.customer.CustomerManagement;
import vn.devpro.shopManagement.Update.product.ProductManagement;



public class UpdateManagement {
	static Scanner sc = new Scanner(System.in);
	public static void execute() {
		do {
			System.out.println("\n--------------CẬP NHẬT THÔNG TIN HỆ THỐNG--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Cập nhật thông tin chủng loại");
			System.out.println("\t2.Cập nhật thông tin hàng hóa");
			System.out.println("\t3.Cập nhật thông tin khách hàng");
			System.out.println("\t0. Quay lại");
			
			System.out.print("Lựa chọn của bạn: ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				CategoryManagement.excute();
				break;// cap nhat thong tin loai hang
			case 2:
				ProductManagement.execute();
				break;// cap nhat thong tin hang hoa
			case 3: 
				CustomerManagement.execute();
				break;//cap nhat thong tin khach hang
			case 0:return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
		}while(true);
		}
}
