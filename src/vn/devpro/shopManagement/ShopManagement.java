package vn.devpro.shopManagement;

import java.util.Scanner;

import vn.devpro.shopManagement.Sale.SaleManagement;
import vn.devpro.shopManagement.Sales_Statistics.OrderManagement;
import vn.devpro.shopManagement.Update.UpdateManagement;
import vn.devpro.shopManagement.Update.category.CategoryManagement;
import vn.devpro.shopManagement.Update.customer.CustomerManagement;
import vn.devpro.shopManagement.Update.product.ProductManagement;


public class ShopManagement {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		CategoryManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
		
		do {
			System.out.println("\n-------------------CHƯƠNG TRÌNH QUẢN LÝ SHOP THỜI TRANG(BT CUỐI KHÓA)------------------");
			System.out.println("Chọn một chức năng:");
			System.out.println("\t1.Cập nhật thông tin hệ thống");
			System.out.println("\t2.Quản lý phiên giao dịch");
			System.out.println("\t3.Quản lý đơn hàng và doanh thu");

			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				UpdateManagement.execute();
				break;
			case 2:
				SaleManagement.execute();
				break;
			case 3:
				OrderManagement.execute();
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}

		} while (true);

	}
}
