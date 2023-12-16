package vn.devpro.shopManagement.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.shopManagement.Update.customer.Customer;
import vn.devpro.shopManagement.Update.customer.CustomerManagement;

public class Order { //tbl_order
	private int id;
	private int customerId;
	private String code;
	//list sản phẩm trong 1 giỏ hàng
	private List<ProductInOrder> ProductsInOrder = new ArrayList<ProductInOrder>(); 

	static Scanner sc = new Scanner(System.in);
	
	public void display() {
		System.out.println("\tMã giỏ hàng: "+ this.code);
		String customerName ="";
		Customer customer = CustomerManagement.findCustomerById(this.customerId);
		if(customer != null) {
			customerName = customer.getName();
		}
		System.out.println("\tTên khách hàng: "+ customerName);
		System.out.println("\tSĐT khách hàng: "+ customer.getMobile());
		System.out.println("\tDanh sách sản phẩm");
		System.out.printf("%-35s %-8s %-15s %-15s%n", "TÊN SẢN PHẨM", "SỐ LƯỢNG",
				"ĐƠN GIÁ", "THÀNH TIỀN");
		
		// hiển thị dữ liệu
		for( ProductInOrder item : ProductsInOrder ) {
			item.display();
		}
		System.out.printf("\t-Tổng thành tiền: %,.2f%n", this.totalCart());
	}
	
	public double totalCart() {
		double totalCart = 0.0f;
		for(ProductInOrder items : ProductsInOrder) {
			totalCart += items.priceToBuy();
		}
		return totalCart;
	}

	// tìm sản phẩm trong giỏ hàng
	public int findProductInOrderById(int id) {
		for(int i =0 ; i< ProductsInOrder.size() ; i++) {
			if(ProductsInOrder.get(i).getProductId()== id) {
				return i;
			}
		}
		return -1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ProductInOrder> getProductsInOrder() {
		return ProductsInOrder;
	}

	public void setProductsInOrder(List<ProductInOrder> productsInOrder) {
		ProductsInOrder = productsInOrder;
	}

	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		Order.sc = sc;
	}

	public Order(int id, int customerId, String code, List<ProductInOrder> productsInOrder) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		ProductsInOrder = productsInOrder;
	}

	public Order() {
		super();
	}
	
}

