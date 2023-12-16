package vn.devpro.shopManagement.Sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.shopManagement.Update.customer.Customer;
import vn.devpro.shopManagement.Update.customer.CustomerManagement;


public class ListOrder {
	private static List<Order> listOrder = new ArrayList<Order>();

	
	public static List<Order> getListOrder() {
		return listOrder;
	}


	public static void setListOrder(List<Order> listOrder) {
		ListOrder.listOrder = listOrder;
	}

    public static void display() {
    	int count = 1;
        System.out.println("\n----------THỐNG KÊ DANH SÁCH ĐƠN ĐẶT HÀNG----------\n");
        
        for (Order order : listOrder) {
        	System.out.printf("Đơn hàng thứ %d\n\n",count++);
            order.display();
            System.out.println("------------------------------------------------------------------"
            		+ "--------------------------------");
        }
    }
    
    // kiểm tra xem order có trong list order hay khong
    public static int findOrderById (int id) {
    	for( int i = 0 ; i < listOrder.size(); i++) {
    		if(listOrder.get(i).getId() == id) {
    			return i;
    		}
    	}
    	return -1;
    }
	// tổng bill 
    public static double totalAllRevenue() {
    	double total = 0;
    	for(Order order : listOrder) {
    		total += order.totalCart();
    	}
    	return total;
    }
 // tìm tên theo id
 	public static String findNameCustomerById(int id) {
 		Customer customer = CustomerManagement.findCustomerById(id);
 		for(int i =0 ; i< listOrder.size() ; i++) {
 			if(listOrder.get(i).getCustomerId() == id) {
 				return customer.getName();
 			}
 		}
 		return null;
 	}
    
    
    
    
    
}
