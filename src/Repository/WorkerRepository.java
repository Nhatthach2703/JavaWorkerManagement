/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DataAcess.WorkerDAO;
import Model.History;
import Model.Worker;
import java.util.ArrayList;

/**
 *
 * @author Xuan Vinh
 */
public class WorkerRepository implements IWorkerRepository{
    @Override
    public void addWorker(ArrayList<Worker> lw) {
        WorkerDAO.Instance().addWorker(lw);
    }

    @Override
    public void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, String status) {
        WorkerDAO.Instance().changeSalary(lw, lh, status);
    }

    @Override
    public void printListHistory(ArrayList<History> lh) {
        WorkerDAO.Instance().printListHistory(lh);
    }
}
