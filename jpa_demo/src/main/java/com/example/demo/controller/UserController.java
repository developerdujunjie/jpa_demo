package com.example.demo.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.ClassRoom;
import com.example.demo.bean.Person;
import com.example.demo.bean.Person1;
import com.example.demo.bean.Student;
import com.example.demo.bean.User;
import com.example.demo.consts.Gender;
import com.example.demo.dao.Car;
import com.example.demo.dao.CarRepository;
import com.example.demo.dao.ClassroomRepository;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.Person1Repository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.UserPagingAndSortingRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.IStudentDTO;
import com.example.demo.dto.IUserDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.StudentDTO2;
import com.example.demo.dto.StudentDTO3;
import com.example.demo.po.Employee;

@Controller
@RequestMapping(path = "/demo")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserPagingAndSortingRepository userPagingAndSortingRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private Person1Repository person1Repository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CarRepository carRepository;

	@GetMapping(path = "/addUser")
	public void addUser(@RequestParam String name, @RequestParam String email) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		userRepository.save(user);
	}

	@GetMapping(path = "/all")
	@ResponseBody
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/getUserByName")
	@ResponseBody
	public List<User> getUsersByName(@RequestParam String name) {
		return userRepository.findByName(name);
	}

	@GetMapping(path = "/page")
	@ResponseBody
	public Page<User> getAllUserByPage() {
		return userPagingAndSortingRepository.findAll(new PageRequest(0, 2, Sort.Direction.ASC, "name"));
	}

	@GetMapping(path = "/distinct")
	@ResponseBody
	public List<User> findDistinct(String name, String email) {
		return userRepository.findDistinctUserByNameAndEmail(name, email);
	}

	@GetMapping(path = "/pageable")
	@ResponseBody
	public List<User> findUserPageablet(String name) {
		Pageable pageable = new PageRequest(0, 2, Direction.DESC, "name");
		return userRepository.findByNameLike("%" + name + "%", pageable);
	}

	@GetMapping(path = "/like")
	@ResponseBody
	public List<User> findLike(String name) {
		return userRepository.findUserDistinctByNameLike("%" + name + "%");
	}

	@GetMapping(path = "/findPersonByName")
	@ResponseBody
	public List<Person> findPersonByName(@RequestParam String name) {
		List<Person> persons = personRepository.findByName(name);
		return persons;
	}

	@GetMapping(path = "/findPersonByCode")
	@ResponseBody
	public List<Person> findPersonByCode(@RequestParam String code) {
		List<Person> persons = personRepository.findByAddress_code(code);
		return persons;
	}

	@GetMapping(path = "/findPerson1ById")
	@ResponseBody
	public Person1 findPerson1ById(@RequestParam Integer id) {
		Person1 person1 = person1Repository.getOne(id);
		return person1;
	}

	@GetMapping(path = "/findStudentById")
	@ResponseBody
	public Optional<Student> findStudentById(@RequestParam Integer id) {
		Optional<Student> op = studentRepository.findById(id);
		Student stu = op.get();
		return op;
	}

	@GetMapping(path = "/all1")
	@ResponseBody
	public Iterable<Student> getAllStudent() {
		List<Student> list = studentRepository.findAll();
		return list;
	}

	@GetMapping(path = "/getClassroomById")
	@ResponseBody
	public ClassroomDTO getClassroomById(@RequestParam Integer id) {
		Optional<ClassRoom> ocr = classroomRepository.findById(id);
		ClassRoom cr = ocr.get();
		List<Student> students = cr.getStudents();
		ClassroomDTO crdto = new ClassroomDTO();
		crdto.setName(cr.getName());
		List<StudentDTO> studtos = crdto.getStudents();
		for (Student stu : students) {
			StudentDTO studto = new StudentDTO();
			studto.setClassname(cr.getName());
			studto.setName(stu.getName());
			studtos.add(studto);
		}
		return crdto;
	}

	@GetMapping(path = "/getIStudentDTOById")
	@ResponseBody
	public IStudentDTO getIStudentDTOById(@RequestParam Integer id) {
		return studentRepository.getIStudentDTOById(id);
	}

	@GetMapping(path = "/findStudentNameById1")
	@ResponseBody
	public StudentDTO2 findStudentNameById1(@RequestParam Integer id) {
		StudentDTO2 sdto2 = studentRepository.findStudentNameById(id, StudentDTO2.class);
		return sdto2;
	}

	@GetMapping(path = "/findStudentNameById2")
	@ResponseBody
	public StudentDTO3 findStudentNameById2(@RequestParam Integer id) {
		StudentDTO3 sdto3 = studentRepository.findStudentNameById(id, StudentDTO3.class);
		return sdto3;
	}

	@GetMapping(path = "/findByEmailAddress")
	@ResponseBody
	public Collection<User> findByEmailAddress(@RequestParam String email) {
		return userRepository.findByEmailAddress(email);
	}

	@GetMapping(path = "/findByNameStartWith")
	@ResponseBody
	public Collection<User> findByNameStartWith(@RequestParam String name) {
		return userRepository.findByNameStartWith(name);
	}

	@GetMapping(path = "/findByNameEndsWith")
	@ResponseBody
	public Collection<User> findByNameEndsWith(@RequestParam String name) {
		return userRepository.findByNameEndsWith(name);
	}

	@GetMapping(path = "/findByNameStartWithOrderById")
	@ResponseBody
	public Collection<User> findByNameStartWithOrderById(@RequestParam String name, @RequestParam String sort) {
		return userRepository.findByNameStartWithOrderById(name, sort);
	}

	@GetMapping(path = "/findByAndSort")
	@ResponseBody
	public List<User> findByAndSort(@RequestParam String name) {
		return userRepository.findByAndSort(name, new Sort(Direction.DESC, "id"));
	}

	@GetMapping(path = "/addCar")
	@ResponseBody
	public Car addCar() {
		Car car = new Car();
		car.setCarName("baoma");
		car.setCreateTime(new Date());
		car.setCreator("djj");
		car.setProductAddress("ruishi");
		car.setProductTime(new Date());
		car.setUpdateTime(new Date());
		car.setUpdator("djj");
		return carRepository.save(car);
	}

	@GetMapping(path = "/findByAsArryAndSort")
	@ResponseBody
	public List<IUserDTO> findByAsArryAndSort(@RequestParam String name) {
		return userRepository.findByAsArryAndSort(name, new Sort(Direction.DESC, "usernameLength"));
	}

	@GetMapping(path = "/findByAsArryAndSortUseParm")
	@ResponseBody
	public List<IUserDTO> findByAsArryAndSortUseParm(@RequestParam String name) {
		return userRepository.findByAsArryAndSortUseParm(name, new Sort(Direction.DESC, "usernameLength"));
	}

	@GetMapping(path = "/findCarByCreator")
	@ResponseBody
	public List<Car> findCarByCreator(String creator) {
		List<Car> cars = carRepository.findByCreator(creator);
		return cars;
	}

	@GetMapping(path = "/deleteUserById")
	@ResponseBody
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "/deleteUserByName")
	@ResponseBody
	public void deleteUserByName(String name) {
		userRepository.deleteByName(name);
	}
	
	@GetMapping(path = "/updateUser")
	@ResponseBody
	public void updateUser(String name1,String name2) {
		userRepository.updateUser(name1, name2);
	}
	
	@GetMapping(path = "/findCarById")
	@ResponseBody
	public Optional<Car> findCarById(int id) {
		Optional<Car> car= carRepository.findById(id);
		return car;
	}
	@GetMapping(path = "/addEmployee")
	@ResponseBody
	public Employee addEmployee() {
		Employee e=new Employee();
		e.setUserName("dujunjie852");
		e.setGender(Gender.MALE);
		return employeeRepository.save(e);
	}
}
