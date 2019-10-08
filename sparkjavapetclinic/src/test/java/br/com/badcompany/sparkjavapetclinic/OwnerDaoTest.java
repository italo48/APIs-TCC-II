package br.com.badcompany.sparkjavapetclinic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.badcompany.sparkjavapetclinic.owner.Owner;
import br.com.badcompany.sparkjavapetclinic.owner.OwnerDao;

public class OwnerDaoTest {
	
	@Mock
	private OwnerDao ownerDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllOwnersTest() {
		List<Owner> o = new ArrayList<>();
		Mockito.when(ownerDao.getAllOwners()).thenReturn(o);
		assertEquals(0, ownerDao.getAllOwners().size());
	}

}
