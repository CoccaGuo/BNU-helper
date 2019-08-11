package ui.plugin.interfaces;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import ui.plugin.calender.SchedulePane;
import util.control.PlugInable;



public class p0Calender implements PlugInable {
    public static final String img = "/util/res/pic/calender.png";
    public static final String title = "课程表";
    AnchorPane ap = null;
  public static Label label = null;
    public static AnchorPane index = null;

    public p0Calender(){
    }

    @Override
    public AnchorPane getAnchorPane() {
//        try{
////            FileInputStream fileIn = new FileInputStream("calender.ser");
////            ObjectInputStream in = new ObjectInputStream(fileIn);
////            ap= (AnchorPane) in.readObject();
////            in.close();
////            fileIn.close();
////        }catch (Exception e){
////            e.printStackTrace();
////            System.out.println("read object-err");
        label.setText("初始化课程表……");
            ap = new SchedulePane();

//            try {
//                File file = new File("calender.ser");
//                if (!file.exists())file.createNewFile();
//                FileOutputStream fileOut =
//                        new FileOutputStream("calender.ser");
//                ObjectOutputStream out = new ObjectOutputStream(fileOut);
//                out.writeObject(e);
//                out.close();
//                fileOut.close();
//            }catch (Exception pane){
//                InformationBox informationBox = new InformationBox(index,"初始化课程表时发生了错误。", 5000);
//                informationBox.setOnMouseClicked(h->{
//                    informationBox.setVisible(false);
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setContentText(pane.toString());
//                    alert.show();
//                });
//                informationBox.show();
//                pane.printStackTrace();
//            }
//
//        }
//

        return ap;
    }

    @Override
    public String getImg() {
        return img;
    }

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setIndexAnchorPane(AnchorPane anchorPane) {
        index = anchorPane;
    }

    @Override
    public void setStatusTag(Label label) {
        this.label = label;
    }


}



