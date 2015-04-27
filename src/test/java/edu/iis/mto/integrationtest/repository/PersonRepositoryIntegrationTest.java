package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import edu.iis.mto.integrationtest.model.Person;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testCanAccessDbAndGetTestData() {
		List<Person> foundTestPersons = personRepository.findAll();
		assertEquals(2, foundTestPersons.size());
	}

	@Test
	public void testSaveNewPersonAndCheckIsPersisted() {
		long count = personRepository.count();
		personRepository.save(a(person().withId(count + 1)
				.withFirstName("Roberto").withLastName("Mancini")));
		assertEquals(count + 1, personRepository.count());
		assertEquals("Mancini", personRepository.findOne(count + 1)
				.getLastName());
	}

	@Test
	public void testUpdatePersonAndCheckIsPersisted() {
		Person exptectedPerson = personRepository.findOne(1L);
		exptectedPerson.setFirstName("CHANGED_NAME");
		personRepository.save(exptectedPerson);

		Person person = personRepository.findOne(1L);
		assertEquals(person, exptectedPerson);
	}

	@Test
	public void testDeletePersonAndCheckIsPersisted() {
		Person person = personRepository.findOne(1L);
		personRepository.delete(person);

		Person personNo2 = personRepository.findOne(1L);
		assertEquals(personNo2, null);
	}

	@Test
	public void test_findByFirstNameLike() {
		long count = personRepository.count();

		List<Person> persons = new ArrayList<Person>();
		persons.add(a(person().withId(count + 1).withFirstName("ZZZdzisiu")));
		persons.add(a(person().withId(count + 2).withFirstName("ZZZdzislaw")));
		personRepository.save(persons);

		List<Person> foundPersons = personRepository.findByFirstNameLike("ZZZ%");
		assertEquals(2, foundPersons.size());
	}

	private Person a(PersonBuilder builder) {
		return builder.build();
	}

}
