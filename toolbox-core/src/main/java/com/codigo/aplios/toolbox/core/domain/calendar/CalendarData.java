package com.codigo.aplios.toolbox.core.domain.calendar;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

// zrobic builder ktorzy to tworzyc tworząc poszczególne elementy
@Entity
@Table(name = "BLC_CALENDAR_ITEM")
public class CalendarData extends Identity {

	private static final long serialVersionUID = -7732348655838633262L;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "DATE", unique = true)
	private Date date;

	@Column(name = "YEAR")
	@NotNull

	private int year;

	@Column(name = "DAYNUMINYEAR")
	@NotNull
	private int dayNumInYear;

	@Column(name = "DAYNUMINMONTH")
	@NotNull
	private int dayNumInMonth;

	@Column(name = "DAYNUMINWEEK")
	@NotNull
	private int dayNumInWeek;

	@NotEmpty
	@Column(name = "MONTH_NAME")
	private String monthName;

	@NotEmpty
	@Column(name = "MONTH_CODE")
	private String monthCode;

	@Column(name = "MONTH_NUM_IN_YEAR")
	private int monthNumInYear;

	@NotNull
	@Column(name = "quaterNum")
	private int quaterNum;

	public Date getDate() {

		return this.date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public int getYear() {

		return this.year;
	}

	public void setYear(int year) {

		this.year = year;
	}

	public int getDayNumInYear() {

		return this.dayNumInYear;
	}

	public void setDayNumInYear(int dayNumInYear) {

		this.dayNumInYear = dayNumInYear;
	}

	public int getDayNumInWeek() {

		return this.dayNumInWeek;
	}

	public void setDayNumInWeek(int dayNumInWeek) {

		this.dayNumInWeek = dayNumInWeek;
	}

	public int getDayNumInMonth() {

		return this.dayNumInMonth;
	}

	public void setDayNumInMonth(int dayNumInMonth) {

		this.dayNumInMonth = dayNumInMonth;
	}

	public String getMonthName() {

		return this.monthName;
	}

	public void setMonthName(String monthName) {

		this.monthName = monthName;
	}

	public int getMonthNumInYear() {

		return this.monthNumInYear;
	}

	public void setMonthNumInYear(int monthNumInYear) {

		this.monthNumInYear = monthNumInYear;
	}

	public int getQuaterNum() {

		return this.quaterNum;
	}

	public void setQuaterNum(int quaterNum) {

		this.quaterNum = quaterNum;
	}

	// @Column(name = "DAYNUMINQUOTER")
	// @NotNull
	// private short dayNumInQuater;
	//

	//
	// @Column(name = "weekNum")
	// @NotNull
	// private short weekNum;
	//
	// //@NotEmpty
	// @Column(name = "MONTH_NAME")
	// private String monthName;
	//
	// //@NotEmpty
	// @Column(name = "MONTH_CODE")
	// private String monthCode;
	//
	// @Column(name = "MONTH_NUM_IN_YEAR")
	// private byte monthNumInYear;
	//
	// @Column(name = "DAYSUFFIX")
	// private String daySuffix;

	// DateKey INT NOT NULL PRIMARY KEY,
	// [Date] DATE NOT NULL,
	// [Day] TINYINT NOT NULL,
	// DaySuffix CHAR(2) NOT NULL,
	// [Weekday] TINYINT NOT NULL,
	// WeekDayName VARCHAR(10) NOT NULL,
	// IsWeekend BIT NOT NULL,
	// IsHoliday BIT NOT NULL,
	// HolidayText VARCHAR(64) SPARSE,
	// DOWInMonth TINYINT NOT NULL,
	// [DayOfYear] SMALLINT NOT NULL,
	// WeekOfMonth TINYINT NOT NULL,
	// WeekOfYear TINYINT NOT NULL,
	// ISOWeekOfYear TINYINT NOT NULL,
	// [Month] TINYINT NOT NULL,
	// [MonthName] VARCHAR(10) NOT NULL,
	// [Quarter] TINYINT NOT NULL,
	// QuarterName VARCHAR(6) NOT NULL,
	// [Year] INT NOT NULL,
	// MMYYYY CHAR(6) NOT NULL,
	// MonthYear CHAR(7) NOT NULL,
	// FirstDayOfMonth DATE NOT NULL,
	// LastDayOfMonth DATE NOT NULL,
	// FirstDayOfQuarter DATE NOT NULL,
	// LastDayOfQuarter DATE NOT NULL,
	// FirstDayOfYear DATE NOT NULL,
	// LastDayOfYear DATE NOT NULL,
	// FirstDayOfNextMonth DATE NOT NULL,
	// FirstDayOfNextYear DATE NOT NULL

	// Calendar Month: The numeric representation of the month, a number from 1-12.
	// Calendar Day: The numeric representation of the calendar day, a number from 1-31. The maximum
	// value depends on the month and on whether it is a leap year.
	// Calendar Year: The numeric representation of the year, such as 1979 or 2017.
	// Calendar Quarter: The numeric representation of the quarter, a number from 1-4.
	// Day Name: The common name for the day of the week, such as Tuesday or Saturday.
	// Day of Week: The numeric representation of the day of the week, a number from 1(Sunday)
	// through 7 (Saturday). In some countries the number 1 is used to represent Monday, though here
	// we will use Sunday for this calculation.
	// Month Name: The common name for the month, such as February or October.

	// Day of Week in Month: The occurrence of a day of week within the current month. Ie: The third
	// Thursday of the current month.
	// Day of Week in Year: The occurrence of a day of week within the current year. Ie: The
	// seventeenth Monday of the current year.
	// Day of Week in Quarter: The occurrence of a day of week within the current quarter. Ie: The
	// seventh Saturday of the current quarter.
	// Day of Quarter: The day number within the current quarter.
	// Day of Year: The day number out of the current year.
	// Week of Month: The week number within the current month. With this calculation, the weeks
	// count starting on the first of the month, regardless of the day of week.
	// Week of Quarter: The week number within the current quarter.
	// Week of Year: The week number within the current year.
	// First Date of Week: The start date of the week. Sunday is assumed here, but could be defined
	// differently.
	// Last Date of Week: The end date of the week. Saturday is assumed here, but could be defined
	// differently.
	// First Date of Month: The first date of the current month.
	// Last Date of Month: The last date of the current month.
	// First Date of Quarter: The first date of the current quarter.
	// Last Date of Quarter: The last date of the current quarter.
	// First Date of Year: The first date of the current year.
	// Last Date of Year: The last date of the current year.
	//
	// CREATE TABLE dbo.Dim_Date
	// ( Calendar_Date DATE NOT NULL CONSTRAINT PK_Dim_Date PRIMARY KEY CLUSTERED, -- The date
	// addressed in this row.
	// Calendar_Date_String VARCHAR(10) NOT NULL, -- The VARCHAR formatted date, such as 07/03/2017
	// Calendar_Month TINYINT NOT NULL, -- Number from 1-12
	// Calendar_Day TINYINT NOT NULL, -- Number from 1 through 31
	// Calendar_Year SMALLINT NOT NULL, -- Current year, eg: 2017, 2025, 1984.
	// Calendar_Quarter TINYINT NOT NULL, -- 1-4, indicates quarter within the current year.
	// Day_Name VARCHAR(9) NOT NULL, -- Name of the day of the week, Sunday...Saturday
	// Day_of_Week TINYINT NOT NULL, -- Number from 1-7 (1 = Sunday)
	// Day_of_Week_in_Month TINYINT NOT NULL, -- Number from 1-5, indicates for example that it's
	// the Nth saturday of the month.
	// Day_of_Week_in_Year TINYINT NOT NULL, -- Number from 1-53, indicates for example that it's
	// the Nth saturday of the year.
	// Day_of_Week_in_Quarter TINYINT NOT NULL, -- Number from 1-13, indicates for example that it's
	// the Nth saturday of the quarter.
	// Day_of_Quarter TINYINT NOT NULL, -- Number from 1-92, indicates the day # in the quarter.
	// Day_of_Year SMALLINT NOT NULL, -- Number from 1-366
	// Week_of_Month TINYINT NOT NULL, -- Number from 1-6, indicates the number of week within the
	// current month.
	// Week_of_Quarter TINYINT NOT NULL, -- Number from 1-14, indicates the number of week within
	// the current quarter.
	// Week_of_Year TINYINT NOT NULL, -- Number from 1-53, indicates the number of week within the
	// current year.
	// Month_Name VARCHAR(9) NOT NULL, -- January-December
	// First_Date_of_Week DATE NOT NULL, -- Date of the first day of this week.
	// Last_Date_of_Week DATE NOT NULL, -- Date of the last day of this week.
	// First_Date_of_Month DATE NOT NULL, -- Date of the first day of this month.
	// Last_Date_of_Month DATE NOT NULL, -- Date of the last day of this month.
	// First_Date_of_Quarter DATE NOT NULL, -- Date of the first day of this quarter.
	// Last_Date_of_Quarter DATE NOT NULL, -- Date of the last day of this quarter.
	// First_Date_of_Year DATE NOT NULL, -- Date of the first day of this year.
	// Last_Date_of_Year DATE NOT NULL, -- Date of the last day of this year.
	// Is_Holiday BIT NOT NULL, -- 1 if a holiday
	// Is_Holiday_Season BIT NOT NULL, -- 1 if part of a holiday season
	// Holiday_Name VARCHAR(50) NULL, -- Name of holiday, if Is_Holiday = 1
	// Holiday_Season_Name VARCHAR(50) NULL, -- Name of holiday season, if Is_Holiday_Season = 1
	// Is_Weekday BIT NOT NULL, -- 1 if Monday-->Friday, 0 for Saturday/Sunday
	// Is_Business_Day BIT NOT NULL, -- 1 if a workday, otherwise 0.
	// Previous_Business_Day DATE NULL, -- Previous date that is a work day
	// Next_Business_Day DATE NULL, -- Next date that is a work day
	// Is_Leap_Year BIT NOT NULL, -- 1 if current year is a leap year.
	// Days_in_Month TINYINT NOT NULL -- Number of days in the current month.
	// );
}
// CREATE TABLE [dbo].[DimDate]
// ( [DateKey] INT primary key,
// [Date] DATETIME,
// [FullDateUK] CHAR(10), -- Date in dd-MM-yyyy format
// [FullDateUSA] CHAR(10),-- Date in MM-dd-yyyy format
// [DayOfMonth] VARCHAR(2), -- Field will hold day number of Month
// [DaySuffix] VARCHAR(4), -- Apply suffix as 1st, 2nd ,3rd etc
// [DayName] VARCHAR(9), -- Contains name of the day, Sunday, Monday
// [DayOfWeekUSA] CHAR(1),-- First Day Sunday=1 and Saturday=7
// [DayOfWeekUK] CHAR(1),-- First Day Monday=1 and Sunday=7
// [DayOfWeekInMonth] VARCHAR(2), --1st Monday or 2nd Monday in Month
// [DayOfWeekInYear] VARCHAR(2),
// [DayOfQuarter] VARCHAR(3),
// [DayOfYear] VARCHAR(3),
// [WeekOfMonth] VARCHAR(1),-- Week Number of Month
// [WeekOfQuarter] VARCHAR(2), --Week Number of the Quarter
// [WeekOfYear] VARCHAR(2),--Week Number of the Year
// [Month] VARCHAR(2), --Number of the Month 1 to 12
// [MonthName] VARCHAR(9),--January, February etc
// [MonthOfQuarter] VARCHAR(2),-- Month Number belongs to Quarter
// [Quarter] CHAR(1),
// [QuarterName] VARCHAR(9),--First,Second..
// [Year] CHAR(4),-- Year value of Date stored in Row
// [YearName] CHAR(7), --CY 2012,CY 2013
// [MonthYear] CHAR(10), --Jan-2013,Feb-2013
// [MMYYYY] CHAR(6),
// [FirstDayOfMonth] DATE,
// [LastDayOfMonth] DATE,
// [FirstDayOfQuarter] DATE,
// [LastDayOfQuarter] DATE,
// [FirstDayOfYear] DATE,
// [LastDayOfYear] DATE,
// [IsHolidayUSA] BIT,-- Flag 1=National Holiday, 0-No National Holiday
// [IsWeekday] BIT,-- 0=Week End ,1=Week Day
// [HolidayUSA] VARCHAR(50),--Name of Holiday in US
// [IsHolidayUK] BIT Null,-- Flag 1=National Holiday, 0-No National Holiday
// [HolidayUK] VARCHAR(50) Null --Name of Holiday in UK
// )
