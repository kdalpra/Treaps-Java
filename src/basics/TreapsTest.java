package basics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapsTest {

	@Test
	//tests add with no given priority
	void test() {
		Treaps<Integer> testTree = new Treaps<Integer>();
		testTree.add(7);
		assertEquals(true,testTree.find(7));
	}
	@Test
	//tests add with key and priority
	void test2() {
		Treaps<Integer> testTree = new Treaps<Integer>();
		testTree.add(5,23532);
		assertEquals(true,testTree.find(5));
	}
	//tests delete
	@Test
	void test3() {
		Treaps<Integer> testTree = new Treaps<Integer>();
		testTree.add(5,100);
		testTree.add(7);
		testTree.delete(5);
		assertEquals(false,testTree.find(5));
	}
	
	
	@Test
	//tests if the tostring and delete method
	void test4() {
		Treaps<Integer> testTree = new Treaps<Integer>();
		Treaps<Integer> testTree2 = new Treaps<Integer>();
		testTree.add(5,10);
		testTree.add(7,20);
		testTree.add(10,22);
		testTree.delete(10);
		
		testTree2.add(5,10);
		testTree2.add(7,20);
		
		
		assertEquals(true,testTree.toString().equals(testTree2.toString()));
	}

}
