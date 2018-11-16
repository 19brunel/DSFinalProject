package list;

import java.awt.Label;

import detail.DetailController;
import detail.DetailWrapper;
import detail.OrderController;
import detail.OrderListWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.FoodItem;
import model.Restaurant;
import model.arraySortedList.ArraySortedList;

public class RestaurantListCell extends ListCell<Restaurant>{
	private HBox content;
    private Text name;
    private Text price;
    private Text description;
    private ImageView icon;
    private VBox vBox;
    private Text dinning;
    private Text address;
    private Text ratingNum;
    private ImageView rating;
    private HBox ratingHbox;
    private Button button;
    private ListWrapper wrap;
    private Restaurant restaurant;

    public RestaurantListCell(ListWrapper wrapper) { //Do text styling here
        super();
        wrap = wrapper;
        String pink = "-fx-fill: rgb(227,28,96);";
        String size = "-fx-font-size: ";
        name = new Text();
        name.setStyle(pink+size+"20px;");
        price = new Text();
        description = new Text();
        description.setWrappingWidth(500);
        dinning = new Text();
        address = new Text();
        ratingNum = new Text();
        rating = new ImageView();
        rating.setFitWidth(50);
        rating.setPreserveRatio(true);
        rating.setTranslateY(4);
        ratingHbox = new HBox(ratingNum, rating, dinning);
        ratingHbox.setSpacing(10);
        vBox = new VBox(name, ratingHbox, description, price, address);
        icon = new ImageView();
        icon.setFitWidth(100);
        icon.setPreserveRatio(true);
        button = new Button();
        content = new HBox(icon, vBox, button);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        restaurant = null;
        button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			OrderController order = new OrderController(new OrderListWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(),restaurant, new ArraySortedList<FoodItem>()));
    			DetailController restaurantView = new DetailController(new DetailWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(),restaurant, order));
    			restaurantView.showStage();
    		}
    	});
        
    }

    @Override
    protected void updateItem(Restaurant item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            name.setText(item.getName());
            price.setText("Price Range: "+item.getPriceRange());
            dinning.setText(item.getCuisineType()+" "+item.getDinningType());
            description.setText(item.getDescription());
            address.setText(item.getAddress());
            ratingNum.setText(item.getAvgRating()+" ");
            icon.setImage(new Image(item.getIconURL()));
            button.setText("VIEW");
            restaurant = item;
            switch((int)item.getAvgRating()) {
            case 0:
    			rating.setImage(new Image("/starIcons/0star.png"));
    			break;
            case 1:
            	rating.setImage(new Image("/starIcons/1star.png"));
            	break;
            case 2:
            	rating.setImage(new Image("/starIcons/2star.png"));
            	break;
            case 3:
            	rating.setImage(new Image("/starIcons/3star.png"));
            	break;
            case 4:
            	rating.setImage(new Image("/starIcons/4star.png"));
            	break;
            case 5:
            	rating.setImage(new Image("/starIcons/5star.png"));
            	break;
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
