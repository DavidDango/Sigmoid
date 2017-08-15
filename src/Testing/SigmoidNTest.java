package Testing;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Neural.SigmoidN;

public class SigmoidNTest {
	static SigmoidN sAND;
	static SigmoidN sOR;
	static SigmoidN sNAND;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		double[] aux = new double[2];
		aux[0] = 0.75;
		aux[1] = 0.75;
		sAND = new SigmoidN(aux, -1);
		double[] aux1 = new double[2];
		aux1[0] = 1.25;
		aux1[1] = 1.25;
		sOR = new SigmoidN(aux1, -1);
		double[] aux2 = new double[2];
		aux2[0] = -0.75;
		aux2[1] = -0.75;
		sNAND = new SigmoidN(aux2, 1);
	}

	@Test
	public void testAND() {
		double[] aux = new double[2];
		aux[0] = 0;
		aux[1] = 0;
		assertEquals(sAND.process_t(aux), 0);
		aux[1] = 1;
		assertEquals(sAND.process_t(aux), 0);
		aux[0] = 1;
		aux[1] = 0;
		assertEquals(sAND.process_t(aux), 0);
		aux[1] = 1;
		System.out.println(sAND.process(aux));
		assertEquals(sAND.process_t(aux), 1);
	}
	
	@Test
	public void testOR() {
		double[] aux = new double[2];
		aux[0] = 0;
		aux[1] = 0;
		assertEquals(sOR.process_t(aux), 0);
		aux[1] = 1;
		assertEquals(sOR.process_t(aux), 1);
		aux[0] = 1;
		aux[1] = 0;
		assertEquals(sOR.process_t(aux), 1);
		aux[1] = 1;
		assertEquals(sOR.process_t(aux), 1);
	}
	
	@Test
	public void testNAND() {
		double[] aux = new double[2];
		aux[0] = 0;
		aux[1] = 0;
		assertEquals(sNAND.process_t(aux), 1);
		aux[1] = 1;
		assertEquals(sNAND.process_t(aux), 1);
		aux[0] = 1;
		aux[1] = 0;
		assertEquals(sNAND.process_t(aux), 1);
		aux[1] = 1;
		assertEquals(sNAND.process_t(aux), 0);
	}
}
