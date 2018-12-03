package detail;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.FoodItem;
import model.Restaurant;
import model.arraySortedList.ArraySortedList;

public class OrderListCell extends ListCell<FoodItem>{
	private Restaurant restaurant;
	private HBox namePrice;
	private Text itemName;
	private Text price;
	private Button button;
	private FoodItem menuItem;
	private ArraySortedList<FoodItem> order;
	private OrderListWrapper wrap;
	public OrderListCell(OrderListWrapper w) {
		super();
		this.wrap = w;
		restaurant = wrap.getRestaurant();
		itemName = new Text();
		itemName.setWrappingWidth(80);
		itemName.minWidth(80);
		price = new Text();
		price.prefWidth(50);
		price.setWrappingWidth(50);
		price.minWidth(50);
		button = new Button();
		namePrice = new HBox(price, itemName, button);
		button.setStyle("-fx-background-color: rgba(0,0,0,0);\r\n" + 
				"-fx-border-color: rgb(227,28,96);\r\n" + 
				"-fx-fill: rgb(227,28,96);\r\n" + 
				"-fx-border-radius: 50;\r\n" + 
				"-fx-background-radius: 50;\r\n" + 
				"-fx-border-width: 2;\r\n");
		namePrice.setStyle("-fx-background-color: #efefef;");
		namePrice.prefHeight(100);
		namePrice.minHeight(100);
		namePrice.setMinHeight(100);
		namePrice.setAlignment(Pos.CENTER_LEFT);
		menuItem = null;
		order = wrap.getOrder();
		button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			order.remove(menuItem);
    			wrap.setOrder(order);
    			OrderController orderCont = new OrderController(wrap);
    			DetailController restaurantView = new DetailController(new DetailWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(),wrap.getRestaurant(), orderCont));
    			restaurantView.showStage();
    		}
    	});
		
	}
	@Override
	protected void updateItem(FoodItem item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            itemName.setText(item.getName());
            price.setText("$"+item.getPrice()+"   ");
            button.setText("REMOVE");
            menuItem = item;
            setGraphic(namePrice);
        } else {
            setGraphic(null);
        }
	}
}
