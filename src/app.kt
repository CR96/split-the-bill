import java.text.NumberFormat

// List of people who will be splitting the bill.
val people = arrayListOf<Person>()

// List of items to be split
val receipt = arrayListOf<Item>()

fun main() {
    readPerson()
    readItem()

    calculateTotals(people, receipt)
}

// These two "read" functions are meant to simulate input through a GUI.
// They ensure UI logic is entirely separated from business logic before
// work on the Android interface begins. Time wasn't taken to sanitize
// or validate inputs here since this project will be released as a
// mobile application, not a command-line program.
fun readPerson() {
    print("Enter name of person: ")
    val name = readLine().toString()

    addPerson(name)

    print("$name added. Add someone else? [y/n] ")

    if (readLine() == "y") {
        readPerson()
    }
}

fun readItem() {
    print("Enter item name: ")
    val name = readLine().toString()

    print("Enter cost of $name: ")
    val price = readLine()?.toDouble()

    val owners = arrayListOf<Person>()

    for (person in people) {
        print("Is ${person.name} helping pay for $name? [y/n] " )

        if (readLine() == "y") {
            owners.add(person)
        }
    }

    if (price != null) {
        addItem(name, price, owners)
    }

    print("$name added. Add another item? [y/n] ")
    if (readLine() == "y") {
        readItem()
    }
}

fun addPerson(name: String) { // Business logic
    val person = Person()
    person.name = name

    people.add(person)
}

fun addItem(name: String, price: Double, owners: ArrayList<Person>) {
    val item = Item()
    item.name = name
    item.price = price
    item.owners = owners

    receipt.add(item)
}

fun calculateTotals(people: ArrayList<Person>, receipt: ArrayList<Item>) {
    for (item in receipt) {
        for (owner in item.owners) {
            // Split item cost evenly between owners
            // TODO: Allow for custom split (e.g. 70/30)
            owner.total += (item.price / item.owners.size)
        }
    }

    val formatter = NumberFormat.getCurrencyInstance()

    for (person in people) {
        println(person.name + ": " + formatter.format(person.total))
    }
}

class Person {
    var name: String = ""
    var total: Double = 0.00
}

class Item {
    var name: String = ""
    var price: Double = 0.00
    var owners = arrayListOf<Person>()
}