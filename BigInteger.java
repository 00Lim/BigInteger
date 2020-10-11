package algorithm;

import java.util.ArrayList;

public class BigInteger
{
	// ū ������ ǥ���� type
	ArrayList<Integer> big;

	/**
	 * ������
	 * @param number ū ������ �Է¹���
	 */
	public BigInteger(String number)
	{
		int size = number.length();

		big = new ArrayList<Integer>();

		// String���� �Է¹��� ���ڸ� list�� �ű�
		// ��ȣ�� ���� ���� �ִ� �� ó�� ���ڴ� ���߿� ó��
		for(int i = size - 1; i > 0; i--)
		{
			big.add(number.charAt(i) - '0');
		}

		// �Է¹��� ������ ��ȣ�� Ȯ���Ѵ�.
		// ������ ��� ��ȣ��Ʈ�� 1
		if(number.charAt(0) == '-')
		{
			big.add(1);
		}
		// 0�̳� ����� ��� ��ȣ��Ʈ�� 0
		else
		{
			big.add(number.charAt(0) - '0');
			big.add(0);
		}
	}

	// 10�� m������ ���ϱ� ����
	public void Multiplication10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			big.add(0, 0);
		}
		
		printNumber();
	}

	// 10�� m������ ������ ����
	public void Division10(int m)
	{
		for(int i = 0; i < m; i++)
		{
			big.remove(0);
		}
		
		printNumber();
	}

	// 10�� m������ ������ ����
	public void Modulo10(int m)
	{
		for(int i = big.size() - 2; i >= m; i--)
		{
			big.remove(i);
		}
		
		printNumber();
	}

	// �� ū ������ ���ϱ� ����
	public void Plus(BigInteger v)
	{
		// ���� ���� �ӽ÷� ������ ����Ʈ
		ArrayList<Integer> temp = new ArrayList<>();
		// �ø���
		int over = 0;
		
		// �� ������ ��ȣ
		int signthis = big.get(big.size() - 1);
		int signv = v.big.get(v.big.size() - 1);
		// �� ������ ũ��
		int sizethis = big.size();
		int sizev = v.big.size();
		
		// �� ū ������ ��ȣ�� ���� ��
		if(signthis == signv)
		{
			// �� ū ������ ũ�Ⱑ ���� ��
			if(sizethis == sizev)
			{
				temp.addAll(big);
				temp.remove(temp.size() - 1);
			}
			// ũ�Ⱑ �ٸ� ��
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
			
			// ���� �����ش�.
			for(int i = 0; i < Math.min(sizethis, sizev) - 1 - signthis; i++)
			{
				temp.set(i, (big.get(i) + v.big.get(i) + over) % 10);
				over = (big.get(i) + v.big.get(i) + over) / 10;
			}
			// ������ �ڸ����� �ø����� �ִٸ�
			if(over == 1)
			{
				// �� ���� �������ش�.
				temp.add(over);
			}
			// ��ȣ��Ʈ ÷��
			temp.add(signthis);
			// ���� ���� �Ű��ش�.
			big.clear();
			big.addAll(temp);
		}
		// �� ū ������ ��ȣ�� �ٸ� ��
		else
		{
			// �� ū ������ ũ�Ⱑ �ٸ� ��
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

	// ū ������ ������ش�.
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