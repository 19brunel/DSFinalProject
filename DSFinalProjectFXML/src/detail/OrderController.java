package detail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.FoodItem;
import model.Restaurant;
import model.arraySortedList.ArraySortedList;

public class OrderController {
	private VBox container;
	private Text title;
	private Text total;
	private ListView orderList;
	private ArraySortedList<FoodItem> order;
	private OrderListWrapper wrap;
	
	public OrderController(OrderListWrapper wrap) {
		order = wrap.getOrder();
		title = new Text("MY ORDER:");
		orderList = new ListView<FoodItem>();
		orderList.setStyle("-fx-background-color: #efefef;");
		orderList.setPadding(new Insets(10));
		this.wrap = wrap;
		total = new Text("TOTAL: $"+this.getOrderTotal());
		container = new VBox(title, orderList, total);
		container.setStyle("-fx-background-color: #efefef;");
		container.setAlignment(Pos.CENTER_LEFT);
		this.createContainer();
	}
	public void createContainer() {
		ObservableList<FoodItem> orderOList = FXCollections.observableArrayList();
    	FoodItem currentItem = null;
    	order.reset();
    	for(int i =0; i<order.size();i++) {
    		currentItem = order.getNext();
    		//System.out.println(currentItem);
    		orderOList.add(currentItem);
    	}
    	orderList.setItems(orderOList);
		orderList.setCellFactory(new Callback<ListView<FoodItem>, ListCell<FoodItem>>() {
            @Override
            public ListCell<FoodItem> call(ListView<FoodItem> listView) {
                return new OrderListCell(new OrderListWrapper(wrap.getEvent(), wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(),wrap.getRestaurant(), order));
            }
        });
	}
	public VBox getContainer() {
		total.setText("TOTAL: $"+this.getOrderTotal());
		this.createContainer();
		return container;
	}
	public void addItem(FoodItem item) {
		order.add(item);
	}
	public ArraySortedList<FoodItem> getOrder(){
		return order;
	}
	public void remove(FoodItem item) {
		order.remove(item);
	}
	public double getOrderTotal() {
		double amount = 0.00;
		order.reset();
		for(int x = 0; x< order.size(); x++) {
			amount+= order.getNext().getPrice();
		}
		return ((double)((int)(amount*100)))/100;
	}
}
