package ru.vallball.kino01.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

import ru.vallball.kino01.service.SessionService;

@Singleton
public class DayList {

	SessionService sessionService;

	private Year year;

	public DayList(SessionService sessionService) {
		this.sessionService = sessionService;
		this.year = Year.now();
	}

	public Year getYear() {
		return year;
	}

	// Получить месяц по номеру
	public Month getMonthName(int i) {
		return year.atMonth(i).getMonth();
	}

	// список месяцев
	public List<Month> listMonths() {
		List<Month> list = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			list.add(this.getMonthName(i));
		}
		return list;
	}

	// Получить количество недель в месяце
	public int getWeeks(YearMonth yearMonth) {
		int daysOfMonths = yearMonth.lengthOfMonth();
		int day = yearMonth.atDay(1).getDayOfWeek().getValue() - 1;
		if (((double) (day + daysOfMonths) / 7) == 4)
			return 4;
		else if ((day + daysOfMonths) / 7 < 5)
			return 5;
		else
			return 6;
	}

	// Получить список всех дней в месяце
	public List<LocalDate> createList(YearMonth yearMonth) {
		List<LocalDate> list = new ArrayList<>();
		for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
			list.add(yearMonth.atDay(i));
		}
		return list;
	}

	// Получить все сеансы в месяце
	public List<Session> listSessions(List<LocalDate> list) {
		List<Session> listSessions = new ArrayList<>();

		return listSessions;
	}

	public List<Week> listWeeks(Month month) {
		YearMonth yearMonth = this.year.atMonth(month.getValue());
		List<Week> listWeek = new ArrayList<>();
		int weeks = getWeeks(yearMonth); // количество недель в месяце
		int beg = yearMonth.atDay(1).getDayOfWeek().getValue(); // номер дня в неделе, с которого начинается месяц
		List<LocalDate> list = createList(yearMonth);// список всех дней в месяце
		Week firstWeek = new Week();
		Iterator<LocalDate> iterator = list.iterator();
		LocalDate date;
		switch (beg) {
		case 1:
			firstWeek.setMon(sessionService.findByDate(date = iterator.next()));
			firstWeek.setMonDate(date);
			iterator.remove();
		case 2:
			firstWeek.setTue(sessionService.findByDate(date = iterator.next()));
			firstWeek.setTueDate(date);
			iterator.remove();
		case 3:
			firstWeek.setWed(sessionService.findByDate(date = iterator.next()));
			firstWeek.setWedDate(date);
			iterator.remove();
		case 4:
			firstWeek.setThu(sessionService.findByDate(date = iterator.next()));
			firstWeek.setThuDate(date);
			iterator.remove();
		case 5:
			firstWeek.setFri(sessionService.findByDate(date = iterator.next()));
			firstWeek.setFriDate(date);
			iterator.remove();
		case 6:
			firstWeek.setSat(sessionService.findByDate(date = iterator.next()));
			firstWeek.setSatDate(date);
			iterator.remove();
		case 7:
			firstWeek.setSun(sessionService.findByDate(date = iterator.next()));
			firstWeek.setSunDate(date);
			iterator.remove();
		}
		listWeek.add(firstWeek);

		for (int i = 2; i < weeks; i++) {
			Week week = new Week();
			week.setMon(sessionService.findByDate(date = iterator.next()));
			week.setMonDate(date);
			iterator.remove();
			week.setTue(sessionService.findByDate(date = iterator.next()));
			week.setTueDate(date);
			iterator.remove();
			week.setWed(sessionService.findByDate(date = iterator.next()));
			week.setWedDate(date);
			iterator.remove();
			week.setThu(sessionService.findByDate(date = iterator.next()));
			week.setThuDate(date);
			iterator.remove();
			week.setFri(sessionService.findByDate(date = iterator.next()));
			week.setFriDate(date);
			iterator.remove();
			week.setSat(sessionService.findByDate(date = iterator.next()));
			week.setSatDate(date);
			iterator.remove();
			week.setSun(sessionService.findByDate(date = iterator.next()));
			week.setSunDate(date);
			iterator.remove();
			listWeek.add(week);
		}

		int size = list.size();
		Week lastWeek = new Week();

		switch (size) {
		case 7:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			lastWeek.setWed(sessionService.findByDate(date = iterator.next()));
			lastWeek.setWedDate(date);
			iterator.remove();
			lastWeek.setThu(sessionService.findByDate(date = iterator.next()));
			lastWeek.setThuDate(date);
			iterator.remove();
			lastWeek.setFri(sessionService.findByDate(date = iterator.next()));
			lastWeek.setFriDate(date);
			iterator.remove();
			lastWeek.setSat(sessionService.findByDate(date = iterator.next()));
			lastWeek.setSatDate(date);
			iterator.remove();
			lastWeek.setSun(sessionService.findByDate(date = iterator.next()));
			lastWeek.setSunDate(date);
			iterator.remove();
			break;

		case 6:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			lastWeek.setWed(sessionService.findByDate(date = iterator.next()));
			lastWeek.setWedDate(date);
			iterator.remove();
			lastWeek.setThu(sessionService.findByDate(date = iterator.next()));
			lastWeek.setThuDate(date);
			iterator.remove();
			lastWeek.setFri(sessionService.findByDate(date = iterator.next()));
			lastWeek.setFriDate(date);
			iterator.remove();
			lastWeek.setSat(sessionService.findByDate(date = iterator.next()));
			lastWeek.setSatDate(date);
			iterator.remove();
			break;

		case 5:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			lastWeek.setWed(sessionService.findByDate(date = iterator.next()));
			lastWeek.setWedDate(date);
			iterator.remove();
			lastWeek.setThu(sessionService.findByDate(date = iterator.next()));
			lastWeek.setThuDate(date);
			iterator.remove();
			lastWeek.setFri(sessionService.findByDate(date = iterator.next()));
			lastWeek.setFriDate(date);
			iterator.remove();
			break;

		case 4:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			lastWeek.setWed(sessionService.findByDate(date = iterator.next()));
			lastWeek.setWedDate(date);
			iterator.remove();
			lastWeek.setThu(sessionService.findByDate(date = iterator.next()));
			lastWeek.setThuDate(date);
			iterator.remove();
			break;

		case 3:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			lastWeek.setWed(sessionService.findByDate(date = iterator.next()));
			lastWeek.setWedDate(date);
			iterator.remove();
			break;

		case 2:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			lastWeek.setTue(sessionService.findByDate(date = iterator.next()));
			lastWeek.setTueDate(date);
			iterator.remove();
			break;

		case 1:
			lastWeek.setMon(sessionService.findByDate(date = iterator.next()));
			lastWeek.setMonDate(date);
			iterator.remove();
			break;
		}
		listWeek.add(lastWeek);
		return listWeek;
	}
}
