// public FlowPane addFlowPane() {
//     FlowPane flow = new FlowPane();
//     flow.setPadding(new Insets(5, 0, 5, 0));
//     flow.setVgap(4);
//     flow.setHgap(4);
//     flow.setPrefWrapLength(170); // preferred width allows for two columns
//     flow.setStyle("-fx-background-color: DAE6F3;");

//     ImageView pages[] = new ImageView[8];
//     for (int i=0; i<8; i++) {
//         pages[i] = new ImageView(
//             new Image(LayoutSample.class.getResourceAsStream(
//             "graphics/chart_"+(i+1)+".png")));
//         flow.getChildren().add(pages[i]);
//     }

//     return flow;
// }