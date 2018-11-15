package detail;


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
import model.Rating;
import model.Restaurant;
import model.arraySortedList.ArraySortedList;

public class ReviewListCell extends ListCell<Rating>{
	private Restaurant restaurant;
	private VBox container;
	private Text description;
	private HBox rate;
	private ImageView ratImage;
	private Text rateNum;
	private Rating rating;
	private DetailWrapper wrap;
	public ReviewListCell(DetailWrapper w) {
		super();
		this.wrap = w;
		restaurant = wrap.getRestaurant();
		description = new Text();
		rateNum = new Text();
		ratImage = new ImageView();
		ratImage.setPreserveRatio(true);
		ratImage.setFitWidth(100);
		rate = new HBox(ratImage, rateNum);
		container = new VBox(rate, description);
		container.setMinHeight(40);
		rating = null;
	}
	@Override
	protected void updateItem(Rating item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            description.setText(item.getDescription());
            rateNum.setText(" "+item.getRating());
            rating = item;
            switch(item.getRating()) {
            case 1:
            	ratImage.setImage(new Image("/starIcons/1star.png"));
            	break;
            case 2:
            	ratImage.setImage(new Image("/starIcons/2star.png"));
            	break;
            case 3:
            	ratImage.setImage(new Image("/starIcons/3star.png"));
            	break;
            case 4:
            	ratImage.setImage(new Image("/starIcons/4star.png"));
            	break;
            case 5:
            	ratImage.setImage(new Image("/starIcons/5star.png"));
            	break;
            }
            setGraphic(container);
        } else {
            setGraphic(null);
        }
	}
}
