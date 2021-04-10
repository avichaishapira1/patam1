package test;

import java.lang.Math;


public class StatLib {
	// simple average
	public static float sum(float[] x){
		float sum_x = 0;
		for(int i=0; i<x.length;i++){
			sum_x += x[i];
		}
		return sum_x;
	}
	public static float avg(float[] x){
		float sum_x = StatLib.sum(x);
		if(sum_x == 0){
			return 0;
		}
		return sum_x/ (float) x.length;
	}

	// returns the variance of X and Y
	public static float var(float[] x){
		float var_x = 0;
		float tohelet = StatLib.avg(x);
		for(int i = 0; i < x.length ; i++){
			var_x += Math.sqrt(x[i] - tohelet);
		}
		return  var_x / x.length;

	}

	// returns the covariance of X and Y
	public static float cov(float x[], float y[])
	{
		float sum = 0;

		for(int i = 0; i < x.length && i<y.length; i++)
			sum += (x[i] - avg(x)) *
					(y[i] - avg(y));
		return sum / (x.length);
	}


	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){
		return (StatLib.cov(x, y) / (float) (Math.sqrt(StatLib.var(x)) * Math.sqrt(StatLib.var(y))));
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float[] x = new float[points.length];
		float[] y = new float[points.length];
		for(int i=0; i< points.length; i++){
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		float a = StatLib.cov(x,y)/StatLib.var(x);
		float b = StatLib.avg(y) - a * StatLib.avg(x);
		return new Line(a, b);
	}

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		Line line = StatLib.linear_reg(points);

		return Math.abs(line.f(p.x) - p.y);
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		return Math.abs(l.f(p.x) - p.y);
	}
	
}
