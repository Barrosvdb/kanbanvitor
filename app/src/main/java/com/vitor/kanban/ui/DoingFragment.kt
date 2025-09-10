package com.vitor.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor.kanban.data.model.Status
import com.vitor.kanban.databinding.FragmentDoingBinding
import com.vitor.kanban.data.model.Task
import com.vitor.kanban.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view, savedInstanceState)

        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()){ task, option -> optionSelected(task,option)}

        with(binding.recyclerviewTask){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Remove ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Edita ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhe ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Movendo done: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Movendo to-do: ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task(id = "10", description = "Revisar tela de login", status = Status.DOING),
            Task(
                id = "11",
                description = "upar projeto no github",
                status = Status.DOING
            ),
            Task(
                id = "12",
                description = "Criar crud da tela de login",
                status = Status.DOING
            ),
            Task(
                id = "13",
                description = "terminar código php",
                status = Status.DOING
            ),
            Task(id = "14", description = "incluir tela de cadastro", status = Status.DOING)
        )
        taskAdapter.submitList(taskList)
    }
}