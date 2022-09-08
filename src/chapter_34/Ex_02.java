package chapter_34;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Ex_02 extends Application {
    private PreparedStatement statement;

    @Override
    public void start(Stage primaryStage) {
        initDB();
        ResultSet resultSet;
        ArrayList<ChartDataPoint> arrayList = new ArrayList<>();

        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(new ChartDataPoint(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ChartDataPoint[] dataPoints = new ChartDataPoint[arrayList.size()];
        arrayList.toArray(dataPoints);
        PieChart pieChart = new PieChart(dataPoints);
        BarChart barChart = new BarChart(dataPoints);

        HBox pane = new HBox(pieChart, barChart);
        HBox.setHgrow(pieChart, Priority.ALWAYS);
        HBox.setHgrow(barChart, Priority.ALWAYS);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_02");
        primaryStage.show();
    }

    private void initDB() {
        {
            try {
                // Establish a connection
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                System.out.println("Database connected");

                String query = "select deptId, count(*) from student where deptId is not null group by deptId";
                statement = connection.prepareStatement(query);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class ChartDataPoint {
        private final String name;
        private final double data;
        private final Color color;

        public ChartDataPoint(String name, double data, Color color) {
            this.name = name;
            this.data = data;
            this.color = color;
        }

        public ChartDataPoint(String name, double data) {
            this(name, data, Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

    static class PieChart extends Pane {
        private final ChartDataPoint[] data;
        private final Arc[] arcs;
        private final Text[] names;

        public PieChart(ChartDataPoint... data) {
            this.data = data;
            arcs = new Arc[data.length];
            names = new Text[data.length];

            initNodes();
            setMinHeight(200);
            setMinWidth(200);
            setPrefSize(200, 200);
        }

        private void initNodes() {
            DoubleBinding centerX = widthProperty().divide(2);
            DoubleBinding centerY = heightProperty().divide(2);
            double totalPoints = 0;
            for (ChartDataPoint dataPoint: data) {
                totalPoints += dataPoint.data;
            }

            double startAngle = 90;
            double length;

            for (int i = 0; i < data.length; i++) {
                names[i] = new Text(data[i].name);

                length = data[i].data / totalPoints * 360;

                Arc arc = new Arc();
                arc.setType(ArcType.ROUND);
                arc.setFill(data[i].color);
                arc.centerXProperty().bind(centerX);
                arc.centerYProperty().bind(centerY);
                arc.setStartAngle(startAngle);
                arc.setLength(length);

                arcs[i] = arc;

                startAngle += length; // Set the startAngle for the next arc
            }
            
            getChildren().addAll(arcs);
            getChildren().addAll(names);
            
            heightProperty().addListener(o -> resizeNodes());
            widthProperty().addListener(o -> resizeNodes());
        }

        private void resizeNodes() {
            double radius = Math.min(getHeight(), getWidth()) * 0.4;
            for (int i = 0; i < data.length; i++) {
                arcs[i].setRadiusX(radius);
                arcs[i].setRadiusY(radius);

                double midAngle = arcs[i].getStartAngle() + arcs[i].getLength() / 2;
                names[i].setX(arcs[i].getCenterX() + radius * Math.cos(Math.toRadians(midAngle)));
                names[i].setY(arcs[i].getCenterY() + radius * Math.sin(Math.toRadians(midAngle)));
            }
        }
    }

    static class BarChart extends Pane {
        private final ChartDataPoint[] data;
        private final Rectangle[] bars;
        private final Text[] names;

        public BarChart(ChartDataPoint... data) {
            this.data = data;
            bars = new Rectangle[data.length];
            names = new Text[data.length];

            initNodes();
            setMinHeight(200);
            setMinWidth(200);
            setPrefSize(200, 200);
        }

        private void initNodes() {
            for (int i = 0; i < data.length; i++) {
                Rectangle bar = new Rectangle();
                bar.setFill(data[i].color);

                bars[i] = bar;
                names[i] = new Text(data[i].name);
            }

            getChildren().addAll(bars);
            getChildren().addAll(names);
            setOnResize();
        }

        private void setOnResize() {
            double highestPoint = 0;
            for (ChartDataPoint dataPoint: data) {
                highestPoint = Math.max(dataPoint.data, highestPoint);
            }

            final double finalHighestPoint = highestPoint;
            heightProperty().addListener((observable, oldValue, newValue) -> {
                double unit = newValue.doubleValue() * 0.9 / finalHighestPoint;
                for (int i = 0; i < bars.length; i++) {
                    bars[i].setHeight(data[i].data * unit);
                    bars[i].setY(newValue.doubleValue() - bars[i].getHeight());
                    names[i].setY(bars[i].getY() - 5);
                }
            });

            widthProperty().addListener((observable, oldValue, newValue) -> {
                double unit = newValue.doubleValue() * 0.95 / (bars.length * 2 + bars.length -1);
                for (int i = 0; i < bars.length; i++) {
                    bars[i].setWidth(unit * 2);
                    bars[i].setX(unit * 3 * i);
                    names[i].setX(bars[i].getX());
                }
            });
        }
    }
}