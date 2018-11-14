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

public class MenuListCell extends ListCell<FoodItem>{
	private Restaurant restaurant;
	private VBox text;
	private HBox namePrice;
	private HBox container;
	private Text itemName;
	private Text description;
	private Text price;
	private Button button;
	private FoodItem menuItem;
	public MenuListCell(DetailWrapper wrap) {
		super();
		restaurant = wrap.getRestaurant();
		itemName = new Text();
		description = new Text();
		description.setWrappingWidth(600);
		price = new Text();
		button = new Button();
		namePrice = new HBox(itemName, price);
		text = new VBox(itemName, description);
		container = new HBox(text, button);
		container.setAlignment(Pos.CENTER);
		menuItem = null;
		button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			OrderController order = wrap.getOrder();
    			order.addItem(menuItem);
    			DetailController restaurantView = new DetailController(new DetailWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(),wrap.getRestaurant(), order));
    			restaurantView.showStage();
    		}
    	});
		
	}
	@Override
	protected void updateItem(FoodItem item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            itemName.setText(""+item);
            //price.setText(" "+item.getPrice());
            description.setText(item.getDescription());
            button.setText("ADD");
            menuItem = item;
            setGraphic(container);
        } else {
            setGraphic(null);
        }
	}
}
