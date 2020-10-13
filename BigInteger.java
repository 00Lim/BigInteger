import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BigInteger
{
	// 큰 정수를 표현할 type
	private LinkedList<Integer> big;

	/**
	 * 생성자
	 * 파라미터가 없으면 0으로 설정
	 */
	public BigInteger()
	{
		this("0");
	}
	
	/**
	 * 생성자
	 * @param number 큰 정수
	 */
	public BigInteger(String number)
	{
		int size = number.length();

		this.big = new LinkedList<Integer>();

		// String으로 입력받은 숫자를 list로 옮김
		// 부호가 있을 수도 있는 맨 처음 글자는 나중에 처리
		for(int i = size - 1; i > 0; i--)
		{
			this.big.add(number.charAt(i) - '0');
		}

		// 입력받은 숫자의 부호를 확인한다.
		// 음수일 경우 부호비트는 1
		if(number.charAt(0) == '-')
		{
			this.big.add(1);
		}
		// 0이나 양수일 경우 부호비트는 0
		else
		{
			this.big.add(number.charAt(0) - '0');
			this.big.add(0);
		}
	}

	// 10의 m제곱과 나누기 연산
	public void Division10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			this.big.remove(0);
		}

		printNumber();
	}
	
	/**
	 * 큰 정수를 반환한다.
	 * @return 큰 정수가 담겨있는 배열
	 */
	public List<Integer> get()
	{
		return this.big;
	}
	
	/**
	 * 큰 정수를 구성하고 있는 한 숫자를 반환한다.
	 * @param index 반환할 숫자의 인덱스
	 * @return 큰 정수의 index 위치의 숫자
	 */
	public int get(int index)
	{
		try
		{
			return this.big.get(index);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return index;
		}
	}
	
	/**
	 * 큰 정수의 부호를 반환한다.
	 * 
	 * @return 부호가 -이면 1, 0이나 양수이면 0을 반환
	 */
	public int getSign()
	{
		return this.big.get(this.big.size() - 1);
	}

	public void Minus(BigInteger v)
	{
		v.big.set(v.big.size() - 1, ~(v.big.get(v.big.size())) + 2);

		Plus(v);
	}

	// 10의 m제곱의 나머지 연산
	public void Modulo10(int m)
	{
		for(int i = this.big.size() - 2; i >= m; i--)
		{
			this.big.remove(i);
		}

		printNumber();
	}

	public void Multiplication(BigInteger v)
	{
		int signthis = this.big.get(this.big.size() - 1);
		int signv = v.big.get(v.big.size() - 1);

		ArrayList<Integer> temp = new ArrayList<>();

		int over = 0;

		for(int i = 0; i < this.big.size() - 1; i++)
		{
			over = 0;

			for(int j = 0; j < v.big.size() - 1; j++)
			{
				if(temp.size() <= i + j)
				{
					temp.add((this.big.get(i) * v.big.get(j) + over) % 10);
					over = (this.big.get(i) * v.big.get(j)) / 10;
				}
				else
				{
					temp.set(i + j, (temp.get(i + j) + this.big.get(i) * v.big.get(j) + over) % 10);
					over = (this.big.get(i) * v.big.get(j)) / 10;
				}
			}
		}
		if(over != 0)
		{
			temp.add(over);
		}
		temp.add(signthis ^ signv);
		this.big.clear();
		this.big.addAll(temp);

		printNumber();
	}

	// 10의 m제곱과 곱하기 연산
	public void Multiplication10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			this.big.add(0, 0);
		}

		printNumber();
	}

	// 두 큰 정수의 더하기 연산
	public void Plus(BigInteger v)
	{
		// 더한 값을 임시로 저장할 리스트
		LinkedList<Integer> temp = new LinkedList<>();
		// 올림수
		int over = 0;

		// 두 정수의 부호
		int signthis = this.big.get(this.big.size() - 1);
		int signv = v.big.get(v.big.size() - 1);
		// 두 정수의 크기
		int sizethis = this.big.size();
		int sizev = v.big.size();

		// 두 큰 정수의 부호가 같을 때
		if(signthis == signv)
		{
			// 두 큰 정수의 크기가 같을 때
			if(sizethis == sizev)
			{
				temp.addAll(this.big);
				temp.remove(temp.size() - 1);
			}
			// 크기가 다를 때
			else if(sizethis > sizev)
			{
				temp.addAll(this.big);
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
				temp.set(i, (this.big.get(i) + v.big.get(i) + over) % 10);
				over = (this.big.get(i) + v.big.get(i) + over) / 10;
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
			this.big.clear();
			this.big.addAll(temp);
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

	// 큰 정수를 출력해준다.
	public void printNumber()
	{
		if(this.big.get(this.big.size() - 1) == 1)
		{
			System.out.print("-");
		}
		for(int i = this.big.size() - 2; i >= 0; i--)
		{
			System.out.print(this.big.get(i));
		}
		System.out.println();
	}
	
	public void set(String number)
	{
		int size = number.length();

		// String으로 입력받은 숫자를 list로 옮김
		// 부호가 있을 수도 있는 맨 처음 글자는 나중에 처리
		for(int i = size - 1; i > 0; i--)
		{
			this.big.add(number.charAt(i) - '0');
		}

		// 입력받은 숫자의 부호를 확인한다.
		// 음수일 경우 부호비트는 1
		if(number.charAt(0) == '-')
		{
			this.big.add(1);
		}
		// 0이나 양수일 경우 부호비트는 0
		else
		{
			this.big.add(number.charAt(0) - '0');
			this.big.add(0);
		}
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
/*
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
		System.out.print("u.Multiple(v) = "); u.Multiplication(v);*/
		
		
	}
}
