package swe2.ss20.ue04.demo.shapes4.app;

/**
 * Test class constructing a snowman as composite object
 */
public class Snowman2 {

//    public static void main(String args[]) {
//
//        Shape snowMan =
//                group(
//                        group(
//                                rect(130, 120, 40, 10),
//                                rect(140, 90, 20, 30)
//                        ),
//                        rect(100, 300, 45, 100),
//                        rect(155, 300, 45, 100),
//                        rect(60, 210, 40, 30),
//                        rect(200, 210, 40, 30),
//                        group(
//                                circle(150, 250, 60),
//                                circle(150, 160, 30)
//                        )
//                );
//
//        Animation snowManAnimation = snowMan.new YAnimation(20);
//        Animation upAnimation = snowMan.createUpAnimation(3);
//        Animation downAnimation = snowMan.createDownAnimation(3);
//        Animation rightAnimation = snowMan.createRightAnimation(3);
//        Animation leftAnimation = snowMan.createLeftAnimation(3);
//
//        Shape ball = circle(240, 190, 30);
//        Shape snowManWithBall = group(snowMan, ball);
//        Animation ballUp = ball.createUpAnimation(8);
//        Animation ballDown = ball.createDownAnimation(8);
//        while (true) {
//            drawAndAnimate(5, snowManWithBall, upAnimation, rightAnimation, ballUp);
//            drawAndAnimate(10, snowManWithBall, leftAnimation);
//            drawAndAnimate(5, snowManWithBall, downAnimation, rightAnimation, ballDown);
//			drawAndAnimate(2, snowManWithBall, new Animation() {
//
//				@Override
//				public void animate() {
//					snowMan.down(10);
//					snowMan.right(10);
//				}
//				
//			}); 
//        }
//    }
//
//    private static void drawAndAnimate(int n, Shape shape, Animation... animations) {
//        for (int i = 0; i < n; i++) {
//            for (Animation a : animations) {
//                a.animate();
//            }
//            Window.clear();
//            shape.draw();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//            }
//        }
//    }

}
