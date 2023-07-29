package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import sample.exceptions.GroupOverflowException;
import sample.exceptions.StudentNotFoundException;
import sample.exceptions.SameIdException;

public class Group {
    private String groupName;
    private List<Student> students = new ArrayList<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group() {

    }

    public void addStudent(Student student) throws SameIdException {

        for (Student member : students) {
            if (member.getId() == student.getId()) {
                throw new SameIdException("ID " + student.getId() + " is already taken");
            }
        }
        students.add(student);
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getLastName().equals(lastName)) {
                return student;
            }
        }
        throw new StudentNotFoundException("Student with last name '" + lastName + "' not found.");
    }

    public boolean removeStudentByID(int id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(id);
                return true;
            }
        }
        throw new StudentNotFoundException("Student with id '" + id + "' not found.");
    }

    public void sortStudentsByLastName() {
        Collections.sort(students, Comparator.nullsLast(new StudentLastNameComparator()));
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        for (Student student : students) {
            student.setGroupName(groupName);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group group = (Group) obj;
        return students.equals(group.students);
        // return Objects.equals(groupName, group.groupName) &&
        // students.equals(group.students);
    }

    public boolean hasSameStudents() {
        int n = 0;
        for (Student student : students) {
            n += 1;
            int n0 = 0;
            for (Student student0 : students) {
                n0 += 1;
                if (student.equals(student0) && n != n0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String students = "";
        for (Student student : this.students) {
            students += System.lineSeparator() + student;
        }
        return groupName + ": " + students;
    }
}