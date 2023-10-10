/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Model.History;
import Model.Worker;
import java.util.ArrayList;

/**
 *
 * @author Xuan Vinh
 */
public interface IWorkerRepository {
    void addWorker(ArrayList<Worker> lw);

    void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, String status);

    void printListHistory(ArrayList<History> lh);
}
