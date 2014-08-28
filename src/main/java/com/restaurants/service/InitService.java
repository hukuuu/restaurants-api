package com.restaurants.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.restaurants.entity.Account;
import com.restaurants.entity.Restaurant;
import com.restaurants.enums.Role;

@Singleton
@Startup
public class InitService {

	@Inject
	private AccountService accountService;

	@Inject
	private RestaurantService restaurantService;

	@PostConstruct
	public void init() {
		if (isSafeToAdd()) {
			initAccounts();
			initRestaurants();
		}
	}

	private boolean isSafeToAdd() {
		List<Account> all = accountService.findAll();
		if(all != null && all.size() > 0)
			return false;
		return true;
	}

	private void initRestaurants() {

		List<Restaurant> rests = new ArrayList<>();

		Restaurant r1 = new Restaurant();
		Restaurant r2 = new Restaurant();
		Restaurant r3 = new Restaurant();
		Restaurant r4 = new Restaurant();
		Restaurant r5 = new Restaurant();
		Restaurant r6 = new Restaurant();
		Restaurant r7 = new Restaurant();
		Restaurant r8 = new Restaurant();
		Restaurant r9 = new Restaurant();
		Restaurant r10 = new Restaurant();
		Restaurant r11 = new Restaurant();
		Restaurant r12 = new Restaurant();

		rests.add(r1);
		rests.add(r2);
		rests.add(r3);
		rests.add(r4);
		rests.add(r5);
		rests.add(r6);
		rests.add(r7);
		rests.add(r8);
		rests.add(r9);
		rests.add(r10);
		rests.add(r11);
		rests.add(r12);
		
		r1.setName("EXOVENT");
		r1.setAddress("164 Jodie Court, Kilbourne, North Carolina");
		r1.setCategory("restaurant");
		r1.setPhone("+359 (899) 407-3518");
		r1.setOpenHours("09:30 - 23:00");

		r2.setName("INCUBUS");
		r2.setAddress("970 Rose Street, Disautel, Rhode Island");
		r2.setCategory("pub");
		r2.setPhone("+359 (879) 546-3521");
		r2.setOpenHours("08:30 - 22:00");

		r3.setName("AVENETRO");
		r3.setAddress("410 Woodside Avenue, Franklin, Alabama");
		r3.setCategory("dinner");
		r3.setPhone("+359 (849) 422-3347");
		r3.setOpenHours("10:00 - 01:00");

		r4.setName("BEDDER");
		r4.setAddress("861 Lott Place, Guilford, Oklahoma");
		r4.setCategory("pub");
		r4.setPhone("+359 (939) 483-3629");
		r4.setOpenHours("09:30 - 19:00");

		r5.setName("SCENTY");
		r5.setAddress("315 Bedell Lane, Coalmont, New Jersey");
		r5.setCategory("restaurant");
		r5.setPhone("+359 (848) 428-2896");
		r5.setOpenHours("08:30 - 23:00");

		r6.setName("ACUSAGE");
		r6.setAddress("267 Tech Place, Fresno, Oregon");
		r6.setCategory("cafe");
		r6.setPhone("+359 (854) 420-3289");
		r6.setOpenHours("07:00 - 20:00");

		r7.setName("HELIXO");
		r7.setAddress("499 Union Avenue, Albany, Wisconsin");
		r7.setCategory("cafe");
		r7.setPhone("+359 (868) 479-3968");
		r7.setOpenHours("10:00 - 23:00");

		r8.setName("HINWAY");
		r8.setAddress("595 Hamilton Walk, Lopezo, Alaska");
		r8.setCategory("restaurant");
		r8.setPhone("+359 (806) 516-3444");
		r8.setOpenHours("07:30 - 20:00");

		r9.setName("COMSTRUCT");
		r9.setAddress("167 Roebling Street, Osage, Pennsylvania");
		r9.setCategory("italian food");
		r9.setPhone("+359 (823) 581-3578");
		r9.setOpenHours("08:30 - 23:00");

		r10.setName("MAXIMIND");
		r10.setAddress("817 Krier Place, Norris, Maryland");
		r10.setCategory("dinner");
		r10.setPhone("+359 (947) 548-3691");
		r10.setOpenHours("10:00 - 23:00");

		r11.setName("ROCKYARD");
		r11.setAddress("853 Woods Place, Lacomb, Colorado");
		r11.setCategory("cafe");
		r11.setPhone("+359 (967) 526-2001");
		r11.setOpenHours("07:30 - 22:00");

		r12.setName("UNISURE");
		r12.setAddress("926 Elm Avenue, Tibbie, New Mexico");
		r12.setCategory("bar");
		r12.setPhone("+359 (808) 524-3702");
		r12.setOpenHours("07:00 - 24:00");

		r1.setAccount(accountService.find(1L));
		r2.setAccount(accountService.find(1L));
		r3.setAccount(accountService.find(1L));

		r4.setAccount(accountService.find(2L));
		r5.setAccount(accountService.find(2L));
		r6.setAccount(accountService.find(2L));

		r7.setAccount(accountService.find(3L));
		r8.setAccount(accountService.find(3L));
		r9.setAccount(accountService.find(3L));

		r10.setAccount(accountService.find(4L));
		r11.setAccount(accountService.find(4L));
		r12.setAccount(accountService.find(4L));

		for (Restaurant restaurant : rests) {
			restaurantService.save(restaurant);
		}

	}

	private void initAccounts() {

		List<Account> accs = new ArrayList<>();

		Account a1 = new Account();
		Account a2 = new Account();
		Account a3 = new Account();
		Account a4 = new Account();

		accs.add(a1);
		accs.add(a2);
		accs.add(a3);
		accs.add(a4);

		a1.setUsername("huku");
		a1.setPassword("huku");
		a2.setUsername("bobec");
		a2.setPassword("bobec");
		a3.setUsername("dinch");
		a3.setPassword("dinch");
		a4.setUsername("zdk");
		a4.setPassword("zdk");
		
		Account a5 = new Account();
		a5.setUsername("Aladdin");
		a5.setPassword("open sesame");
		accs.add(a5);

		for (Account account : accs) {
			accountService.register(account);
		}

	}

}
