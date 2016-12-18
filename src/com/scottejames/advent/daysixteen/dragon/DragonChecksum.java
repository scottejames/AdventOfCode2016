package com.scottejames.advent.daysixteen.dragon;

public class DragonChecksum {

	public static String mungeData(String data) {
		StringBuffer right = new StringBuffer();
		for (int i = data.length(); i != 0; i--) {
			if (data.charAt(i-1) == '1')
				right.append('0');
			else
				right.append('1');
		}
		return data + "0" + right.toString();

	}
	public static String checkSum(String data){
		StringBuffer chksm = new StringBuffer();
		for (int i = 0; i < data.length(); i+=2){
			if(data.charAt(i) == data.charAt(i+1))
				chksm.append('1');
			else 
				chksm.append('0');
		}
		if( chksm.length() % 2 == 0 ) {
            return checkSum(chksm.toString());
        }
        else {
            return chksm.toString();
        }

	}
	
	public static String generateString(String seed, int length){
		String result = seed;
		while (result.length() < length){
			result = mungeData(result);
		}
		return result.substring(0,length);
	}

	public static void main(String[] args) {

		System.out.println(checkSum(generateString("10111011111001111",35651584)));

	}
}
