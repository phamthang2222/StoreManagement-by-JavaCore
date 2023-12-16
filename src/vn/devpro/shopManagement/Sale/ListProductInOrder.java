package vn.devpro.shopManagement.Sale;

import java.util.ArrayList;
import java.util.List;

public class ListProductInOrder {
	// list tất cả sản phẩm đã được order
	private static List<ProductInOrder> listProductInOrder = new ArrayList<ProductInOrder>();
	
	public static List<ProductInOrder> getListProductInOrder() {
		return listProductInOrder;
	}

	public static void setListProductInOrder(List<ProductInOrder> listProductInOrder) {
		ListProductInOrder.listProductInOrder = listProductInOrder;
	}
	
	public void displayProductWasBought() {
		int stt = 1;
		compressionItem();
		System.out.printf("%-3s %-35s %-20s %15s%n","STT","TÊN SẢN PHẨM",
				"TỔNG SỐ LƯỢNG ĐÃ BÁN","DOANH THU");
		for(ProductInOrder item: listProductInOrder) {
			System.out.printf("%3d ",stt++);
			item.displayProductWasBought();
		}
	}
	// nếu trong danh sách có 2 sản phẩm trùng id thì cộng SL 2 sp vào 
	public void compressionItem() {
		for(int i = 0; i<listProductInOrder.size(); i ++) {
			for( int j = i+1 ; j < listProductInOrder.size(); j++) {
				
				int quantityItem1 = listProductInOrder.get(i).getQuanity();
				int quantityItem2 = listProductInOrder.get(j).getQuanity();
		
				if(listProductInOrder.get(i).getProductId() == listProductInOrder.get(j).getProductId()) {
					listProductInOrder.get(i).setQuanity(quantityItem1 + quantityItem2);
					listProductInOrder.remove(listProductInOrder.get(j));
				}
			}
		}
	}
	
	
}
