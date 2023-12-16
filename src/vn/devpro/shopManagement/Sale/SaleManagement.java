package vn.devpro.shopManagement.Sale;

import java.util.Scanner;

import vn.devpro.shopManagement.Update.customer.Customer;
import vn.devpro.shopManagement.Update.customer.CustomerManagement;
import vn.devpro.shopManagement.Update.product.ProductManagement;

public class SaleManagement {
	//cần  1 giỏ hàng
	// các thao tác của KH
	//	+thêm hàng vào giỏ
	//	+Thay đổi số lượng
	//	+xóa sp khỏi giỏ
	//	+tạo hóa đơn
	//  +hiển thị hóa đơn
	public static int autoId = 1;
	public static int autoIdOrder = 1;
	// tạo 1 cart mới
	private static Order orders = new Order();
	
	static Scanner sc = new Scanner(System.in);
	public static void execute() {
		do {
			System.out.println("\n--------------QUẢN LÝ GIỎ HÀNG--------------");
			System.out.println("Chọn chức năng cập nhật:");
			System.out.println("\t1.Hiển thị giỏ hàng");
			System.out.println("\t2.Thêm sản phẩm vào giỏ hàng");
			System.out.println("\t3.Thay đổi số lượng ");
			System.out.println("\t4.Xóa sản phẩm khỏi ");
			System.out.println("\t5.Tạo hóa đơn");
			System.out.println("\t6.Lưu hóa đơn");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				System.out.println("\n\t\tGIỎ HÀNG CỦA BẠN");
				if(orders.getProductsInOrder().size() <= 0) {
					System.out.println("-Không có sản phẩm nào trong giỏ hàng");
					
				}else {
					orders.display();
				}
				break;// Hiển thị giỏ hàng
			case 2:
				addtoCart();
				break;// Thêm sản phẩm vào giỏ hàng
			case 3: 
				changeProductQuanity();
				break;//Thay đổi số lượng
			case 4: 
				deleteCartProduct();
				break;//xoa giỏi hàng
			case 5: 
				createBill();
				break;//tạo hóa đơn
			case 6: 
				saveBill();
				break;//lưu hóa đơn 
			case 0: 
				return;
			default:
				System.out.println("-Lựa chọn không hợp lệ!");
			}
		}while(true);
	}
	
	private static void addtoCart() {
		System.out.println("\n-----------THÊM SẢN PHẨM VÀO GIỎ-----------");
		System.out.printf("\tNhập id sản phẩm muốn mua: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		//kiểm tra sản phẩm này có trong ds sp hay ko
		int productIndex = ProductManagement.findProductByID(productId);
		if(productIndex == -1) {
			System.out.println("\tSản phẩm không có trong ds sản phẩm");
			return;
		}
		//có thì nhập số lượng
		System.out.print("\tNhập số lượng cần mua: ");
		int quantity= Integer.parseInt(sc.nextLine());
		if(quantity< 0) {
			System.out.print("\tSố lượng mua không hợp lệ");
			return;
		}
		//cập nhật sản phẩm vào giỏ hàng: có 2 trường hợp
		//+ TH1: sản phẩm chưa có trong giỏ hàng -> thêm mới
		//+ TH2: sản phẩm đã có trong giỏi hàng -> tăng số lượng
		
		//tìm sp xem có trong giỏ hàng chưa
		int productInOrderIndex = orders.findProductInOrderById(productId);
		// tính tổng số lượng hàng dự kiến
		if(productInOrderIndex != -1 ) { // sp có trong giỏ
			
			//tổng của sl mới nhật và sl đã có trong giỏ
			quantity+= orders.getProductsInOrder().get(productInOrderIndex).getQuanity();
		}
		
		//cập nhật giỏ hàng
		//TH1
		if(productInOrderIndex == -1) {
			orders.getProductsInOrder().add(new ProductInOrder(ProductInOrder.autoId++, productId,autoIdOrder, quantity));
		}//TH2
		else {
			orders.getProductsInOrder().get(productInOrderIndex).setQuanity(quantity);
		}
		System.out.println("-> Thêm thành công!");
		
	}
	private static void changeProductQuanity() {
		System.out.println("\n-----------THAY ĐỔI SỐ LƯỢNG SẢN PHẨM TRONG GIỎ-----------");
		System.out.printf("\tNhập id sản phẩm muốn thay đổi: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		//kiểm tra sản phẩm này có trong ds sp hay ko
		int productIndex = orders.findProductInOrderById(productId);
		if(productIndex == -1) {
			System.out.println("\tSản phẩm không có trong giỏ hàng");
			return;
		}
		
		//có thì nhập số lượng
		System.out.print("\tNhập số lượng cần thêm(+) / bớt(-): ");
		int quantity= Integer.parseInt(sc.nextLine());
		
		//tính số lượng sau khi thêm bớt
		quantity+= orders.getProductsInOrder().get(productIndex).getQuanity();
		
		// nếu số lượng sau khi thêm bớt > 0 -> thay đổi quantity
		if(quantity> 0 ) { 
			orders.getProductsInOrder().get(productIndex).setQuanity(quantity);
		}else { // ngược lại nếu số lượng sau khi bớt mà < 0 -> remove item khỏi list
			orders.getProductsInOrder().remove(productIndex);
		}

		System.out.println("-> Thay đổi thành công");
	}
	private static void deleteCartProduct() {
		System.out.println("\n-----------XÓA SẢN PHẨM TRONG GIỎ-----------");
		System.out.printf("\tNhập id sản phẩm muốn xóa: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		//kiểm tra sản phẩm này có trong ds sp hay ko
		int productIndex = orders.findProductInOrderById(productId);
		if(productIndex == -1) {
			System.out.println("\tSản phẩm không có trong giỏ hàng");
			return;
		}
		orders.getProductsInOrder().remove(productIndex);
		System.out.println("-> Xóa thành công!");
		
	}
	private static void createBill() {
		System.out.println("\n------------------HÓA ĐƠN CỦA BẠN------------------");
		//cập nhật thông tin khách hàng
		System.out.print("\tNhập id khách hàng: ");
		int customerId = Integer.parseInt(sc.nextLine());
		int customerIndex = CustomerManagement.findCustomerByID(customerId);
		String customerName;
		String customerCode = null;
		String customerMobile;
		if(customerIndex == -1 ) { // khách mới
			do {
				System.out.print("\tNhập tên khách hàng: ");
				customerName = sc.nextLine();
			}while(customerName.isEmpty());
			
			do {
				System.out.print("\tNhập code khách hàng: ");
				customerCode = sc.nextLine();
				
			}while(customerCode.isEmpty());
			
			do {
				System.out.print("\tNhập SĐT khách hàng: ");
				customerMobile = sc.nextLine();
			}while(customerMobile.isEmpty());
			
			// thêm khách hàng vào danh sách khách hàng
			Customer newCustomer = new Customer(customerId, customerCode, customerName, customerMobile);
			CustomerManagement.customers.add(newCustomer);
			
		}else {// tên đã tồn tại trong danh sách thì lấy ra
			customerName = CustomerManagement.customers.get(customerIndex).getName();
			customerCode = CustomerManagement.customers.get(customerIndex).getCode();
			customerMobile = CustomerManagement.customers.get(customerIndex).getMobile();
		}
		orders = new Order(autoIdOrder, customerId, "HD_" + customerCode, orders.getProductsInOrder());

		//hiển thị
		orders.display();
		
	}
	private static void saveBill() {
		//1. lưu hóa đơn vào danh sách hóa đơn
		//2. lưu các sp trong hóa đơn vào product_in_order.
		
		//nếu chưa có sp nào trong giỏ hàng hoặc chưa tạo hóa đơn thì lưu thất bại
		if(orders.getProductsInOrder().size() == 0 || orders.getCode() == null) {
			System.out.println("\tHóa đơn chưa có sản phẩm nào! \n\t->Lưu hóa đơn thất bại!");
		}else {
			// Lưu đối tượng Order vào ListOrder
	        ListOrder.getListOrder().add(orders);
	        
			 // Lưu danh sách sản phẩm vào ListProductInOrder
	        ListProductInOrder.getListProductInOrder().addAll(orders.getProductsInOrder());

			System.out.println("-> Lưu hóa đơn thành công!");
			
			//tăng id của hóa đơn lên
			autoIdOrder++;
			
	        // Reset giỏ hàng sau khi lưu hóa đơn
	        orders = new Order();
		}
	}
		

}
