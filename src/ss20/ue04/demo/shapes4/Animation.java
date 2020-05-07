package ss20.ue04.demo.shapes4;

public interface Animation {
    void animate();
    
    public static interface Special extends Animation {
    	
    }
    
    public static class MA implements Animation {

		@Override
		public void animate() {
			
		}
    	
    }
}
