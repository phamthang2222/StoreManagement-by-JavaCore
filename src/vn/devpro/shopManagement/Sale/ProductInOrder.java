package vn.devpro.shopManagement.Sale;

import vn.devpro.shopManagement.Update.product.Product;
import vn.devpro.shopManagement.Update.product.ProductManagement;

public class ProductInOrder { 
	private int id;
	private int productId;
	private int orderId;
	private int quanity;
	
	static int autoId = 1;
	
	public void display() {
		// lấy ra sản phẩm dựa theo ID 
		Product product = ProductManagement.getProductById(this.productId);
		System.out.printf("%-35s %8d %,15.2f %,15.2f%n", product.getName(), this.quanity,
				product.getPrice(), priceToBuy());	
	}
	public void displayProductWasBought() {
		// lấy ra sản phẩm dựa theo ID 	
		Product product = ProductManagement.getProductById(this.productId);
		System.out.printf(" %-35s %10d %,25.2f %n", product.getName(), this.quanity,
				 priceToBuy());
	}
	
	public double priceToBuy() {
		Product product = ProductManagement.getProductById(this.productId);
		return this.quanity * product.getPrice();
	}

	public ProductInOrder(int id, int productId, int orderId, int quanity) {
		super();
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.quanity = quanity;
	}
	public ProductInOrder() {
		super();
	}
	public int getID() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	
}
