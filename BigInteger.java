package algorithm;

import java.util.ArrayList;

public class BigInteger
{
	// 큰 정수를 표현할 type
	ArrayList<Integer> big;

	/**
	 * 생성자
	 * @param number 큰 정수를 입력받음
	 */
	public BigInteger(String number)
	{
		int size = number.length();

		big = new ArrayList<Integer>();

		// String으로 입력받은 숫자를 list로 옮김
		// 부호가 있을 수도 있는 맨 처음 글자는 나중에 처리
		for(int i = size - 1; i > 0; i--)
		{
			big.add(number.charAt(i) - '0');
		}

		// 입력받은 숫자의 부호를 확인한다.
		// 음수일 경우 부호비트는 1
		if(number.charAt(0) == '-')
		{
			big.add(1);
		}
		// 0이나 양수일 경우 부호비트는 0
		else
		{
			big.add(number.charAt(0) - '0');
			big.add(0);
		}
	}

	// 10의 m제곱과 곱하기 연산
	public void Multiplication10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			big.add(0, 0);
		}
		
		printNumber();
	}

	// 10의 m제곱과 나누기 연산
	public void Division10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			big.remove(0);
		}
		
		printNumber();
	}

	// 10의 m제곱의 나머지 연산
	public void Modulo10(int m)
	{
		for(int i = big.size() - 2; i >= m; i--)
		{
			big.remove(i);
		}
		
		printNumber();
	}

	// 두 큰 정수의 더하기 연산
	public void Plus(BigInteger v)
	{
		// 더한 값을 임시로 저장할 리스트
		ArrayList<Integer> temp = new ArrayList<>();
		// 올림수
		int over = 0;
		
		// 두 정수의 부호
		int signthis = big.get(big.size() - 1);
		int signv = v.big.get(v.big.size() - 1);
		// 두 정수의 크기
		int sizethis = big.size();
		int sizev = v.big.size();
		
		// 두 큰 정수의 부호가 같을 때
		if(signthis == signv)
		{
			// 두 큰 정수의 크기가 같을 때
			if(sizethis == sizev)
			{
				temp.addAll(big);
				temp.remove(temp.size() - 1);
			}
			// 크기가 다를 때
			else if(sizethis > sizev)
			{
				temp.addAll(big);
				temp.remove(temp.size() - 1);
			}
			else
			{
				temp.addAll(v.big);
				temp.remove(temp.size() - 1);
			}
			
			// 값을 더해준다.
			for(int i = 0; i < Math.min(sizethis, sizev) - 1 - signthis; i++)
			{
				temp.set(i, (big.get(i) + v.big.get(i) + over) % 10);
				over = (big.get(i) + v.big.get(i) + over) / 10;
			}
			// 마지막 자리에서 올림수가 있다면
			if(over == 1)
			{
				// 그 값을 포함해준다.
				temp.add(over);
			}
			// 부호비트 첨가
			temp.add(signthis);
			// 더한 값을 옮겨준다.
			big.clear();
			big.addAll(temp);
		}
		// 두 큰 정수의 부호가 다를 때
		else
		{
			// 두 큰 정수의 크기가 다를 때
			if(sizethis > sizev)
			{
				
			}
		}
		
		printNumber();
	}

	public void Minus(BigInteger v)
	{
		v.big.set(v.big.size() - 1, ~(v.big.get(v.big.size())) + 2);
		
		Plus(v);
	}
	
	public void Multiple(BigInteger v)
	{
		int signthis = big.get(big.size() - 1);
		int signv = v.big.get(v.big.size() - 1);
		
		ArrayList<Integer> temp = new ArrayList<>();
		
		int over = 0;
		
		for(int i = 0; i < big.size() - 1; i++)
		{
			over = 0;
			
			for(int j = 0; j < v.big.size() - 1; j++)
			{
				if(temp.size() <= i + j)
				{
					temp.add((big.get(i) * v.big.get(j) + over) % 10);
					over = (big.get(i) * v.big.get(j)) / 10;
				}
				else
				{
					temp.set(i + j, (temp.get(i + j) + big.get(i) * v.big.get(j) + over) % 10);
					over = (big.get(i) * v.big.get(j)) / 10;
				}
			}
		}
		if(over != 0)
		{
			temp.add(over);
		}
		temp.add(signthis ^ signv);
		big.clear();
		big.addAll(temp);
		
		printNumber();
	}

	// 큰 정수를 출력해준다.
	public void printNumber()
	{
		if(big.get(big.size() - 1) == 1)
		{
			System.out.print("-");
		}
		for(int i = big.size() - 2; i >= 0; i--)
		{
			System.out.print(big.get(i));
		}
		System.out.println();
	}
	
	public static void main(String args[])
	{
		BigInteger u = new BigInteger("52465312");
		BigInteger v = new BigInteger("8986451325");
		
		System.out.print("BigNumber u =   ");
		u.printNumber();
		System.out.print("BigNumber v = ");
		v.printNumber();
		System.out.println();
		
		System.out.print("u.Multiplication10(3) = "); u.Multiplication10(3);
		u = new BigInteger("52465312");
		v = new BigInteger("8986451325");
		System.out.print("v.Division10(3) = "); v.Division10(3);
		u = new BigInteger("52465312");
		v = new BigInteger("8986451325");
		System.out.print("u.Modulo10(13) = "); u.Modulo10(13);
		u = new BigInteger("52465312");
		v = new BigInteger("8986451325");
		System.out.print("u.Plus(v) =  "); u.Plus(v);
		u = new BigInteger("52465312");
		v = new BigInteger("8986451325");
		System.out.print("u.Minus(v) = \n"); //u.Minus(v);
		u = new BigInteger("52465312");
		v = new BigInteger("8986451325");
		System.out.print("u.Multiple(v) = "); u.Multiple(v);
	}
}