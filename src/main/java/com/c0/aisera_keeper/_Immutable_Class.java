package com.c0.aisera_keeper;

import java.util.List;
/*
 Immutable Class
✅ No setters
✅ All fields final
✅ Class final (so no subclass can mutate it)
✅ Mutable fields (if any) are defensively copied
Thread-safety - Immutable objects are inherently thread-safe — no synchronization required, since state never changes.
Cache keys / HashMap safety - Immutable objects are perfect as keys in collections (HashMap, HashSet) because:
	- Hashcode never changes → lookup stays valid.
	- No accidental corruption due to state mutation.
Safe for caching and reuse
Security
Simpler debugging and reasoning
Functional programming compatibility - Immutable data fits naturally with functional-style programming — you create new objects rather than mutating existing ones. Example: Java Streams + lambdas prefer immutable data flows.
Defensive copying - When you expose internal mutable state (like a Date), you can return an immutable copy:
	public Date getStartDate() {
    		return new Date(startDate.getTime()); // defensive copy
	}

🧩 Common Real-World Uses
Use Case                    Example
Shared                      config or settings	Immutable “Config” objects (I covered it)
Value Objects (DDD)	        Money, Address, Coordinate
Keys in maps	UUID,         composite key
Thread-safe caching	        Immutable cache entries
Functional transformations	Streams & pure functions
Constant data models	      Product definitions, version info

⚙️ Immutable class checklist
Mark the class final.
Make all fields private final.
Initialize all fields in the constructor.
Do not provide setters.
If a field is mutable (like a list), defensively copy it in getter and constructor.
 */

public final class _Immutable_Class {
  private final String name;
  private final List<String> subjects;

  public _Immutable_Class(String name, List<String> subjects) {
    this.name = name;
    this.subjects = List.copyOf(subjects); // defensive copy (Java 10+)
  }

  public String getName() { return name; }
  public List<String> getSubjects() { return subjects; } // already unmodifiable
}

