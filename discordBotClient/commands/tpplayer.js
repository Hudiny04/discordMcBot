const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('tpplayer')
        .setDescription('teleporting player to another player on server')
        .addStringOption(option => option.setName('target1').setDescription('who will be teleported'))
        .addStringOption(option => option.setName('target2').setDescription('to who will be teleported'), true),
    async execute(interaction) {
        const target1 = interaction.options.get('target1').value;
        const target2 = interaction.options.get('target2').value;
         axios.get('http://127.0.0.1:8001/teleport-player', {
            params: {
                target1: target1,
                target2: target2
            }
         }
        ).then(function (response) {
            interaction.reply(response.data);
        }).catch(function (error) {
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};