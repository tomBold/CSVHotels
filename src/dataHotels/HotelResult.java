package dataHotels;

import java.util.ArrayList;
import java.util.List;

public class HotelResult {
	private String name;
	List<Integer> prices;
	int max;
	int min;
	List<Integer> normalPrices;

	public HotelResult(String name, HotelDates hotelDate) {
		super();

		this.name = name;

		int minP = -1;
		int maxP = 0;

		prices = new ArrayList<>();
		normalPrices = new ArrayList<>();

		for (CheckinDatePrices check : hotelDate.getCheckinDates()) {
			if (check.getPrice1() != -1) {

				if (minP == -1) {
					minP = check.getPrice1();
				} else if (check.getPrice1() < minP) {
					minP = check.getPrice1();
				}

				if (check.getPrice1() > maxP) {
					maxP = check.getPrice1();
				}
			}

			if (check.getPrice2() != -1) {
				if (minP == -1) {
					minP = check.getPrice2();
				} else if (check.getPrice2() < minP) {
					minP = check.getPrice2();
				}

				if (check.getPrice2() > maxP) {
					maxP = check.getPrice2();
				}
			}

			if (check.getPrice3() != -1) {
				if (minP == -1) {
					minP = check.getPrice3();
				} else if (check.getPrice3() < minP) {
					minP = check.getPrice3();
				}

				if (check.getPrice3() > maxP) {
					maxP = check.getPrice3();
				}
			}

			if (check.getPrice4() != -1) {
				if (minP == -1) {
					minP = check.getPrice4();
				} else if (check.getPrice4() < minP) {
					minP = check.getPrice4();
				}

				if (check.getPrice4() > maxP) {
					maxP = check.getPrice4();
				}
			}

			prices.add(check.getPrice1());
			prices.add(check.getPrice2());
			prices.add(check.getPrice3());
			prices.add(check.getPrice4());

		}

		this.max = maxP;
		this.min = minP;

		toNorma();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getPrices() {
		return prices;
	}

	public void setPrices(List<Integer> prices) {
		this.prices = prices;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	private void toNorma() {
		for (Integer curr : prices) {
			if (curr != -1) {
				double x = ((double) (curr - getMin())) / ((double) (getMax() - getMin()));
				normalPrices.add((int) Math.round(x * 100));
			} else {
				normalPrices.add(-1);
			}
		}
	}

	public List<Integer> getNormalPrices() {
		return normalPrices;
	}

	public void setNormalPrices(List<Integer> normalPrices) {
		this.normalPrices = normalPrices;
	}

}
