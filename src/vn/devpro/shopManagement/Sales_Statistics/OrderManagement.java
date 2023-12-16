package vn.devpro.shopManagement.Sales_Statistics;

import java.util.Scanner;
import vn.devpro.shopManagement.Sale.ListOrder;
import vn.devpro.shopManagement.Sale.ListProductInOrder;

public class OrderManagement {
	
	static Scanner sc = new Scanner(System.in);
	static ListProductInOrder listProductInOrder = new ListProductInOrder();
	
	public static void execute() {
		do {
			System.out.println("\n--------------QUẢN LÝ GIỎ HÀNG--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Hiển thị danh sách đơn hàng");
			System.out.println("\t2.Xóa 1 đơn hàng khỏi danh sách");
			System.out.println("\t3.Hiển thị tổng doanh thu có được từ tất cả các hóa đơn");
			System.out.println("\t4.Hiển thị tổng số tiền thu được theo khách hàng");
			System.out.println("\t5.Hiển thị tổng số tiền thu được theo sản phẩm đã bán");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				display();
				break;// Hiển thị ds đơn hàng
			case 2:
				deleteABill();
				break;// Xóa 1 đơn hàng khỏi danh sách
			case 3: 
				totalAllRevenue();
				break;//tổng doanh thu có được từ tất cả các hóa đơn
			case 4: 
				totalRevenueByCustomer();
				break;//tổng số tiền thu được theo khách hàng
			case 5: 
				totalRevenueByProduct();
				break;//tổng số tiền thu được theo sản phẩm đã bán
			case 0: 
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
		}while(true);
	}
	private static void display() {
		if(ListOrder.getListOrder().size() == 0) {
			System.out.println("\n-> Chưa có hóa đơn nào!");
		}else {
			ListOrder.display();
		}
		
	}
	private static void deleteABill() {
		System.out.println("\n-----------XÓA ĐƠN HÀNG TRONG DANH SÁCH-----------");
		System.out.printf("\tNhập id đơn hàng muốn xóa: ");
		int orderId = Integer.parseInt(sc.nextLine());
		
		//kiểm tra sản phẩm này có trong ds sp hay ko
		int orderIndex = ListOrder.findOrderById(orderId);
		if(orderIndex == -1) {
			System.out.println("\tHóa đơn không có trong danh sách");
			return;
		}else {
		ListOrder.getListOrder().remove(orderIndex);
		System.out.println("-> Xóa thành công!");
		}
	}
	private static void totalAllRevenue() {
		System.out.println("\n-----------TỔNG DOANH THU TẤT CẢ CÁC HÓA ĐƠN-----------");
		System.out.printf("\t-Tổng doanh thu: %,.2f\n",ListOrder.totalAllRevenue());
	}
	private static void totalRevenueByCustomer() {
		System.out.println("\n-----------TỔNG DOANH THU THEO KHÁCH HÀNG-----------");
		System.out.print("\tNhập id khách hàng muốn tính doanh thu: ");
		
		int customerID = Integer.parseInt(sc.nextLine());
		
		//lấy tên theo id
		String customerName = ListOrder.findNameCustomerById(customerID);
		
		double total = 0;
    	for( int i = 0 ; i < ListOrder.getListOrder().size() ; i++) {
    		if(ListOrder.getListOrder().get(i).getCustomerId() == customerID) {
    			total += ListOrder.getListOrder().get(i).totalCart();
    		}
    	} 
    	if( total == 0) {
    		System.out.println("\t-> Khách hàng chưa có hóa đơn mua hàng!");
    	}else {
    		System.out.println("\t   Khách hàng: " + customerName);
    		System.out.printf("\t-> Tổng doanh thu của %-17s là: %,.2f",customerName,total);
    	}
	}
	private static void totalRevenueByProduct() {
		if(ListProductInOrder.getListProductInOrder().size() != 0) {
			System.out.println("\n\t\t-----------TỔNG DOANH THU THEO SẢN PHẨM-----------");
			listProductInOrder.displayProductWasBought();
		}else {
			System.out.println("\tChưa bán được sản phẩm nào!");
		}
	}
}
