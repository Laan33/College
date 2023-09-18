1. Booking must be made with a vehicle reg num, a test centre (a test centre should be represented with a test centre name and test centre address) and a date and time. Use the LocalDateTime class from the java.time library as the type for the date and time variable.
2. Should be possible to query for the test centre of the booking, return value should be value that was set when booking created
3. Should be possible to query for the registration number of the booking, return value should be value that was set when booking created, or the value entered in the last edit
4. Should be possible to edit the vehicle registration number of the booking
5. Should be possible to create a new booking without specifying a date and time. 
	In such cases, the date and time should be set by an external API. You can assume the interface below is available to you for this.	
```java
	public interface NCTBookingSlotWebservice {
			public LocalDateTime getBookingDateTime(TestCentre testCentre);
	}```
6. Should not be possible to create a booking with a date and time in the past. Invalid dates
and times should be handled with an appropriate user defined exception.
7. Bookings are to have a unique Booking ID Number that is generated at their creation.
8. A toString() method should return a string representation of the booking in the following format:
```
Booking ID Number: 58964158
Registration Number: 16-WH-59741
Centre: Ballinasloe
Address: Unit 9, Pollboy Industrial Estate,
Ballinasloe, Galway H53 NW94
Date & Time: On Friday, 22 September 2023 at 08:00
```
