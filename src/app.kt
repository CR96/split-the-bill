import java.text.NumberFormat

fun main() {
    // Get list of people who will be splitting the bill.
    val aaron = Person()
    aaron.name = "Aaron"

    val corey = Person()
    corey.name = "Corey"

    val lizzi = Person()
    lizzi.name = "Lizzi"

    val people = arrayOf(aaron, corey, lizzi)

    // Create items. Will be created from deserialized JSON eventually.
    // We'll use eggs, milk, and cereal as an example.

    val eggs = Item()
    eggs.name = "Meijer Grade-A Cage Free Large Eggs, 12 Count"
    eggs.price = 1.69
    eggs.quantity = 1 // 1 unit (i.e. one carton of 12 eggs)

    val milk = Item()
    milk.name = "Meijer 1% Lowfat Milk, Half Gallon"
    milk.price = 1.29
    milk.quantity = 2

    val cereal = Item()
    cereal.name = "Honey Bunches of Oats Almond, 18 oz."
    cereal.price = 3.59
    cereal.quantity = 1

    // Assign "owners" to each item based on their array index.
    eggs.owners = arrayOf(0, 1, 2) // Everyone shares the eggs
    milk.owners = arrayOf(1, 2) // The milk is split between Corey and Lizzi
    cereal.owners = arrayOf(1) // The cereal is just for Corey

    val receipt = arrayOf(eggs, milk, cereal)

    calculateTotals(people, receipt)
}

fun calculateTotals(people: Array<Person>, receipt: Array<Item>) {
    for (item in receipt) {
        for (owner in item.owners) {
            // Split item cost evenly between owners
            // TODO: Allow for custom split (e.g. 70/30)
            people[owner].total += (item.price / item.owners.size)
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
    var quantity: Int = 1
    var owners = arrayOf(0)
}